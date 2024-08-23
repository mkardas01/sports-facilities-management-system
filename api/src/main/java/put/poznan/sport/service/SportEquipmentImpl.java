package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import put.poznan.sport.dto.SportEquipment.CreateSportEquipment;
import put.poznan.sport.dto.SportEquipment.UpdateSportEquipment;
import put.poznan.sport.entity.SportEquipment;
import put.poznan.sport.exception.exceptionClasses.EquipmentOwnershipNotFoundException;
import put.poznan.sport.exception.exceptionClasses.SportEquipmentNotFoundException;
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
                .amount(equipment.getAmount())
                .ownerID(equipment.getSportFacilityId())
                .build();

        return sportEquipmentRepository.save(newSportEquipment);

    }

    @Override
    public SportEquipment updateEquipment(UpdateSportEquipment requestEquipment) {

        userService.checkIfUserIsManager(requestEquipment.getSportFacilityId());

        SportEquipment oldEquipment = sportEquipmentRepository.findByIdAndOwnerID(requestEquipment.getId(), requestEquipment.getSportFacilityId())
                .orElseThrow(() -> new SportEquipmentNotFoundException("Nie znaleziono przedmiotu z przesłanym id " + requestEquipment.getId()));

        SportEquipment newEquipment = SportEquipment.builder()
                .id(oldEquipment.getId())
                .type(requestEquipment.getType())
                .brand(requestEquipment.getBrand())
                .model(requestEquipment.getModel())
                .description(requestEquipment.getDescription())
                .imageUrl(requestEquipment.getImageUrl())
                .amount(requestEquipment.getAmount())
                .ownerID(oldEquipment.getOwnerID())
                .build();


        return sportEquipmentRepository.save(newEquipment);
    }

    @Override
    public void deleteEquipment(int equipmentDeleteID) {

        SportEquipment equipment = sportEquipmentRepository.findById(equipmentDeleteID)
                .orElseThrow(() -> new SportEquipmentNotFoundException("Nie znaleziono przedmiotu o id: " + equipmentDeleteID));

        userService.checkIfUserIsManager(equipment.getOwnerID());

        sportEquipmentRepository.deleteById(equipmentDeleteID);
    }
}
