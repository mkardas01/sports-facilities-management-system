package put.poznan.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.TrainingSessionParticipant;
import put.poznan.sport.entity.TrainingSessionParticipantId;

public interface TrainingSessionParticipantRepository extends JpaRepository<TrainingSessionParticipant, TrainingSessionParticipantId> {
}