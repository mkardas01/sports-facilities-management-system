package put.poznan.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.SportFacilityNews;

public interface SportFacilityNewsRepository extends JpaRepository<SportFacilityNews, Integer> {
}