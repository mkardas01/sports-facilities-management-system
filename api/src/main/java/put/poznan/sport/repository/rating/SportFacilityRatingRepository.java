package put.poznan.sport.repository.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.entity.User;
import put.poznan.sport.entity.rating.SportFacilityRating;
import put.poznan.sport.entity.rating.TrainingSessionRating;

import java.util.Optional;

public interface SportFacilityRatingRepository extends JpaRepository<SportFacilityRating, Integer> {

    Optional<SportFacilityRating> findByUserAndSportFacility(User user, SportFacility sportFacility);

    Optional<SportFacilityRating> findByUserAndId(User user, Integer id);


}