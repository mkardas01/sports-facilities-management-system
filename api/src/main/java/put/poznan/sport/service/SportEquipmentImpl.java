package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import put.poznan.sport.dto.SportEquipment.CreateSportEquipment;
import put.poznan.sport.entity.EquipmentOwnership;
import put.poznan.sport.entity.SportEquipment;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.entity.User;
import put.poznan.sport.exception.exceptionClasses.SportEquipmentNotFoundException;
import put.poznan.sport.exception.exceptionClasses.UserNotFoundException;
import put.poznan.sport.repository.EquipmentOwnershipRepository;
import put.poznan.sport.repository.SportEquipmentRepository;
import put.poznan.sport.repository.SportFacilityRepository;
import put.poznan.sport.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
                .orElseThrow(() -> new SportEquipmentNotFoundException("SportEquipment with id " + id + " not found"));
    }

    @Override
    @Transactional
    public SportEquipment createEquipment(CreateSportEquipment equipment) {

        Optional<SportFacility> sportFacility = sportFacilityRepository.findById(equipment.getSportFacilityId());
        userService.checkIfUserIsManager(sportFacility);

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
                .sportFacilitiesId(sportFacility.get().getId())
                .amount(1)
                .build();

        equipmentOwnershipRepository.save(ownership);

        return savedSportEquipment;

    }

    @Override
    public SportEquipment updateEquipment(SportEquipment equipment) {
        sportEquipmentRepository.findById(equipment.getId())
                .orElseThrow(() -> new SportEquipmentNotFoundException("SportEquipment with id " + equipment.getId() + " not found"));

        return sportEquipmentRepository.save(equipment);
    }

    @Override
    public boolean deleteEquipment(int id) {
        SportEquipment equipment = sportEquipmentRepository.findById(id)
                .orElseThrow(() -> new SportEquipmentNotFoundException("SportEquipment with id " + id + " not found"));

        sportEquipmentRepository.deleteById(id);
        return true;
    }
}
