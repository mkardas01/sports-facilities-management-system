package put.poznan.sport.repository.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.rating.CoachRating;
import put.poznan.sport.entity.rating.TrainingSessionRating;

public interface CoachRatingRepository extends JpaRepository<CoachRating, Integer> {
}