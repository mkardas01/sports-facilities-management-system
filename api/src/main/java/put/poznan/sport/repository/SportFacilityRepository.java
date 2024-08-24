package put.poznan.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import put.poznan.sport.entity.SportEquipment;
import put.poznan.sport.entity.SportFacility;

import java.util.List;
import java.util.Optional;

public interface SportFacilityRepository extends JpaRepository<SportFacility, Integer> {
}