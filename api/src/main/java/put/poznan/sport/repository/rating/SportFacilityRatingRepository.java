package put.poznan.sport.repository.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.rating.SportFacilityRating;
import put.poznan.sport.entity.rating.TrainingSessionRating;

public interface SportFacilityRatingRepository extends JpaRepository<SportFacilityRating, Integer> {
}