package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.SportEquipment;
import put.poznan.sport.repository.SportEquipmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SportEquipmentImpl implements SportEquipmentService {

    @Autowired
    private SportEquipmentRepository sportEquipmentRepository;

    public List<SportEquipment> getAllEquipments() { return sportEquipmentRepository.findAll(); }

    public SportEquipment getEquipmentsById(int id) {
        Optional<SportEquipment> optional = sportEquipmentRepository.findById(id);
        return optional.orElse(null);
    }

    public SportEquipment createEquipment(SportEquipment equipment) { return sportEquipmentRepository.save(equipment); }

    public SportEquipment updateEquipment(SportEquipment equipment) {
        return sportEquipmentRepository.save(equipment);
    }

    public boolean deleteEquipment(int id) {
        Optional<SportEquipment> optional = sportEquipmentRepository.findById(id);
        if (optional.isPresent()) {
            sportEquipmentRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

}
