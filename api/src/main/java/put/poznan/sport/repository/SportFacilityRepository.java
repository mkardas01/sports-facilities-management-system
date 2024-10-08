package put.poznan.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import put.poznan.sport.entity.*;
import put.poznan.sport.entity.rating.CoachRating;
import put.poznan.sport.entity.rating.SportFacilityRating;

import java.util.List;
import java.util.Optional;

public interface SportFacilityRepository extends JpaRepository<SportFacility, Integer> {
}