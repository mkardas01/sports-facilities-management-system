package put.poznan.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.TrainingSession;

import java.util.List;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Integer> {
    List<TrainingSession> findBySportFacilityId(int sportFacilityId);
}