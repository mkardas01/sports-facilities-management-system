package put.poznan.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.OpenHour;

public interface OpenHourRepository extends JpaRepository<OpenHour, Integer> {
}