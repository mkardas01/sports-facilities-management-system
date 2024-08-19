package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.SportEquipment;
import put.poznan.sport.exception.exceptionClasses.SportEquipmentNotFoundException;
import put.poznan.sport.repository.SportEquipmentRepository;

import java.util.List;

@Service
public class SportEquipmentImpl implements SportEquipmentService {

    @Autowired
    private SportEquipmentRepository sportEquipmentRepository;

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
    public SportEquipment createEquipment(SportEquipment equipment) {
        return sportEquipmentRepository.save(equipment);
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
