package put.poznan.sport.repository.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.rating.TrainingSessionRating;

public interface TrainingSessionRatingRepository extends JpaRepository<TrainingSessionRating, Integer> {
}