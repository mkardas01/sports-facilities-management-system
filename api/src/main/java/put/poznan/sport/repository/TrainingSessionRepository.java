package put.poznan.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.TrainingSession;

import java.util.List;
import java.util.Optional;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Integer> {
    Optional<List<TrainingSession>> findBySportFacilityId(int sportFacilityId);
}