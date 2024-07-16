package put.poznan.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.TrainingSession;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Integer> {
}