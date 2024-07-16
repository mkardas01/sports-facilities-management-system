package put.poznan.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.SportFacilityParticipant;
import put.poznan.sport.entity.SportFacilityParticipantId;

public interface SportFacilityParticipantRepository extends JpaRepository<SportFacilityParticipant, SportFacilityParticipantId> {
}