package put.poznan.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}