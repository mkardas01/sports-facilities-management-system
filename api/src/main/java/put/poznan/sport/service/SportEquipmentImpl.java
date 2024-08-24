package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import put.poznan.sport.dto.SportEquipment.CreateSportEquipment;
import put.poznan.sport.dto.SportEquipment.UpdateSportEquipment;
import put.poznan.sport.entity.SportEquipment;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.exception.exceptionClasses.EquipmentOwnershipNotFoundException;
import put.poznan.sport.exception.exceptionClasses.SportEquipmentNotFoundException;
import put.poznan.sport.exception.exceptionClasses.SportFacilityNotFoundException;
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
    private  SportFacilityRepository sportFacilityRepository;

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

       SportFacility sportFacility = sportFacilityRepository.findById(equipment.getSportFacilityId())
                .orElseThrow(() -> new SportFacilityNotFoundException("Nie znaleziono obiektu sportowego o podanym id: " + equipment.getSportFacilityId()));

        userService.checkIfUserIsManager(sportFacility);

        SportEquipment newSportEquipment = SportEquipment.builder()
                .type(equipment.getType())
                .model(equipment.getModel())
                .brand(equipment.getBrand())
                .description(equipment.getDescription())
                .imageUrl(equipment.getImageUrl())
                .amount(equipment.getAmount())
                .ownerSportFacility(sportFacility)
                .build();

        return sportEquipmentRepository.save(newSportEquipment);

    }

    @Override
    public SportEquipment updateEquipment(UpdateSportEquipment requestEquipment) {


        SportEquipment oldEquipment = sportEquipmentRepository.findById(requestEquipment.getId())
                .orElseThrow(() -> new SportEquipmentNotFoundException("Nie znaleziono przedmiotu: " + requestEquipment.getBrand() + requestEquipment.getModel()));

        userService.checkIfUserIsManager(oldEquipment.getOwnerSportFacility());

        SportEquipment newEquipment = SportEquipment.builder()
                .id(oldEquipment.getId())
                .type(requestEquipment.getType())
                .brand(requestEquipment.getBrand())
                .model(requestEquipment.getModel())
                .description(requestEquipment.getDescription())
                .imageUrl(requestEquipment.getImageUrl())
                .amount(requestEquipment.getAmount())
                .ownerSportFacility(oldEquipment.getOwnerSportFacility())
                .build();


        return sportEquipmentRepository.save(newEquipment);
    }

    @Override
    public void deleteEquipment(int equipmentDeleteID) {

        SportEquipment equipment = sportEquipmentRepository.findById(equipmentDeleteID)
                .orElseThrow(() -> new SportEquipmentNotFoundException("Nie znaleziono przedmiotu o id: " + equipmentDeleteID));

        userService.checkIfUserIsManager(equipment.getOwnerSportFacility());

        sportEquipmentRepository.deleteById(equipmentDeleteID);
    }
}
