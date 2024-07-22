package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.EquipmentOwnership;
import put.poznan.sport.entity.EquipmentOwnershipId;
import put.poznan.sport.repository.EquipmentOwnershipRepository;

import java.util.List;
import java.util.Optional;


public interface EquipmentOwnershipService {

    public  List<EquipmentOwnership> getAllEqOw();

    public EquipmentOwnership getEqOwById(EquipmentOwnershipId id);

    public EquipmentOwnership saveEqOw(EquipmentOwnership equipmentOwnership);

    //public EquipmentOwnership updateEqOw(EquipmentOwnership equipmentOwnership);

    public boolean deleteEqOw(EquipmentOwnershipId id);

}
