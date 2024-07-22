package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.EquipmentOwnership;
import put.poznan.sport.entity.EquipmentOwnershipId;
import put.poznan.sport.repository.EquipmentOwnershipRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentOwnershipImpl implements EquipmentOwnershipService {

    @Autowired
    EquipmentOwnershipRepository equipmentOwnershipRepository;

    public List<EquipmentOwnership> getAllEqOw(){ return equipmentOwnershipRepository.findAll(); }

    public EquipmentOwnership getEqOwById(EquipmentOwnershipId id){
        Optional<EquipmentOwnership> equipmentOwnership = equipmentOwnershipRepository.findById(id);
        return equipmentOwnership.orElse(null);
    }

    public EquipmentOwnership saveEqOw(EquipmentOwnership equipmentOwnership){
        return equipmentOwnershipRepository.save(equipmentOwnership);
    }

//    public EquipmentOwnership updateEqOw(EquipmentOwnership equipmentOwnership){
//        return equipmentOwnershipRepository.save(equipmentOwnership);
//    }

    public boolean deleteEqOw(EquipmentOwnershipId id){
        Optional<EquipmentOwnership> equipmentOwnership = equipmentOwnershipRepository.findById(id);
        if(equipmentOwnership.isPresent()){
            equipmentOwnershipRepository.delete(equipmentOwnership.get());
            return true;

        }else{
            return false;
        }

    }
}
