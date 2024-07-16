package put.poznan.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.Coach;

public interface CoachRepository extends JpaRepository<Coach, Integer> {
}
