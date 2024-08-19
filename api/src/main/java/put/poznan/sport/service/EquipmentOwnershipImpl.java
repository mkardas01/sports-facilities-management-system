package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.EquipmentOwnership;
import put.poznan.sport.entity.EquipmentOwnershipId;
import put.poznan.sport.exception.exceptionClasses.EquipmentOwnershipNotFoundException;
import put.poznan.sport.repository.EquipmentOwnershipRepository;

import java.util.List;

@Service
public class EquipmentOwnershipImpl implements EquipmentOwnershipService {

    @Autowired
    EquipmentOwnershipRepository equipmentOwnershipRepository;

    @Override
    public List<EquipmentOwnership> getAllEqOw() {
        return equipmentOwnershipRepository.findAll();
    }

    @Override
    public EquipmentOwnership getEqOwById(EquipmentOwnershipId id) {
        return equipmentOwnershipRepository.findById(id)
                .orElseThrow(() -> new EquipmentOwnershipNotFoundException("Equipment ownership with id " + id + " not found"));
    }

    @Override
    public EquipmentOwnership saveEqOw(EquipmentOwnership equipmentOwnership) {
        return equipmentOwnershipRepository.save(equipmentOwnership);
    }

    @Override
    public boolean deleteEqOw(EquipmentOwnershipId id) {
        EquipmentOwnership equipmentOwnership = equipmentOwnershipRepository.findById(id)
                .orElseThrow(() -> new EquipmentOwnershipNotFoundException("Equipment ownership with id " + id + " not found"));

        equipmentOwnershipRepository.delete(equipmentOwnership);
        return true;
    }
}
