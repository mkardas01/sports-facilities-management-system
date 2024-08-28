package put.poznan.sport.repository.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.entity.User;
import put.poznan.sport.entity.rating.CoachRating;
import put.poznan.sport.entity.rating.TrainingSessionRating;

import java.util.Optional;

public interface CoachRatingRepository extends JpaRepository<CoachRating, Integer> {
    Optional<CoachRating> findByUserAndCoach(User user, Coach coach);

    Optional<CoachRating> findByUserAndId(User user, Integer id);
}