package put.poznan.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.SportEquipment;

public interface SportEquipmentRepository extends JpaRepository<SportEquipment, Integer> { }