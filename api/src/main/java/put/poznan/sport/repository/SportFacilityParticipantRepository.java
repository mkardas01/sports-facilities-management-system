package put.poznan.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.entity.SportFacilityParticipant;
import put.poznan.sport.entity.SportFacilityParticipantId;
import put.poznan.sport.entity.User;

import java.util.List;
import java.util.Optional;

public interface SportFacilityParticipantRepository extends JpaRepository<SportFacilityParticipant, SportFacilityParticipantId> {
    Optional <SportFacilityParticipant> findByUserAndSportFacilityAndIsActive(User user, SportFacility sportFacility, Integer isActive );
    Optional<List<SportFacilityParticipant>> findAllByUser(User user);
    Optional<List<SportFacilityParticipant>> findAllBySportFacilitiesId(Integer facilityId);
    Boolean existsSportFacilityParticipantByUserIdAndSportFacilitiesId(Integer userId, Integer SportFacilityId);

}