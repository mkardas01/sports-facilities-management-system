package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import put.poznan.sport.dto.SportEquipment.CreateSportEquipment;
import put.poznan.sport.dto.SportEquipment.UpdateSportEquipment;
import put.poznan.sport.entity.EquipmentOwnership;
import put.poznan.sport.entity.SportEquipment;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.exception.exceptionClasses.EquipmentOwnershipNotFoundException;
import put.poznan.sport.exception.exceptionClasses.SportEquipmentNotFoundException;
import put.poznan.sport.exception.exceptionClasses.SportFacilityNotFoundException;
import put.poznan.sport.repository.EquipmentOwnershipRepository;
import put.poznan.sport.repository.SportEquipmentRepository;
import put.poznan.sport.repository.SportFacilityRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SportEquipmentImpl implements SportEquipmentService {

    @Autowired
    private SportEquipmentRepository sportEquipmentRepository;

    @Autowired
    private UserImpl userService;

    @Autowired
    private SportFacilityRepository sportFacilityRepository;

    @Autowired
    private EquipmentOwnershipRepository equipmentOwnershipRepository;

    @Override
    public List<SportEquipment> getAllEquipments() {
        return sportEquipmentRepository.findAll();
    }

    @Override
    public SportEquipment getEquipmentsById(int id) {
        return sportEquipmentRepository.findById(id)
                .orElseThrow(() -> new SportEquipmentNotFoundException(" Nie znaleziono wyposażenia z podanym id: " + id ));
    }

    @Override
    @Transactional
    public SportEquipment createEquipment(CreateSportEquipment equipment) {
        userService.checkIfUserIsManager(equipment.getSportFacilityId());

        SportEquipment newSportEquipment = SportEquipment.builder()
                .type(equipment.getType())
                .model(equipment.getModel())
                .brand(equipment.getBrand())
                .description(equipment.getDescription())
                .imageUrl(equipment.getImageUrl())
                .build();

        SportEquipment savedSportEquipment = sportEquipmentRepository.save(newSportEquipment);

        EquipmentOwnership ownership = EquipmentOwnership.builder()
                .sportEquipmentId(savedSportEquipment.getId())
                .sportFacilitiesId(equipment.getSportFacilityId())
                .amount(equipment.getAmount())
                .build();

        equipmentOwnershipRepository.save(ownership);

        return savedSportEquipment;

    }

    @Override
    public SportEquipment updateEquipment(UpdateSportEquipment requestEquipment) {

        userService.checkIfUserIsManager(requestEquipment.getSportFacilityId());
        this.checkIfEquipmentOwner(requestEquipment.getId(), requestEquipment.getSportFacilityId());

        SportEquipment oldEquipment = sportEquipmentRepository.findById(requestEquipment.getId())
                .orElseThrow(() -> new SportEquipmentNotFoundException("Nie znaleziono obiektu sportowego z przesłanym id " + requestEquipment.getId()));

        SportEquipment newEquipment = SportEquipment.builder()
                .id(oldEquipment.getId())
                .type(requestEquipment.getType())
                .brand(requestEquipment.getBrand())
                .model(requestEquipment.getModel())
                .description(requestEquipment.getDescription())
                .equipmentOwnerships(oldEquipment.getEquipmentOwnerships())
                .imageUrl(requestEquipment.getImageUrl())
                .build();


        return sportEquipmentRepository.save(newEquipment);
    }

    @Override
    public void deleteEquipment(int equipmentDeleteID) {

        EquipmentOwnership equipmentOwnership = equipmentOwnershipRepository
                .findEquipmentOwnershipsBySportEquipmentId(equipmentDeleteID)
                .orElseThrow(() -> new SportEquipmentNotFoundException("Nie znaleziono przedmiotu o id: " + equipmentDeleteID));

        userService.checkIfUserIsManager(equipmentOwnership.getSportFacilitiesId());
        this.checkIfEquipmentOwner(equipmentDeleteID, equipmentOwnership.getSportFacilitiesId());

        sportEquipmentRepository.deleteById(equipmentDeleteID);
    }

    private void checkIfEquipmentOwner(Integer equipmentID, Integer sportFacilityID){
        Optional<EquipmentOwnership> equipmentOwnership = equipmentOwnershipRepository.findEquipmentOwnershipsBySportEquipmentId(equipmentID);

        if (equipmentOwnership.isEmpty() || !Objects.equals(equipmentOwnership.get().getSportFacilitiesId(), sportFacilityID)){
            throw new EquipmentOwnershipNotFoundException("Nie możesz edytować tego przedmiotu");
        }
    }
}
