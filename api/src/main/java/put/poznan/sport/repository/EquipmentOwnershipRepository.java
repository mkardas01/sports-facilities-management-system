package put.poznan.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import put.poznan.sport.entity.EquipmentOwnership;
import put.poznan.sport.entity.EquipmentOwnershipId;

import java.util.Optional;

@Repository
public interface EquipmentOwnershipRepository extends JpaRepository<EquipmentOwnership, EquipmentOwnershipId> {
    Optional<EquipmentOwnership> findEquipmentOwnershipsBySportEquipmentId(Integer id);
}
