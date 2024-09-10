package put.poznan.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.SportFacilityNews;

import java.util.List;

public interface SportFacilityNewsRepository extends JpaRepository<SportFacilityNews, Integer> {
    List<SportFacilityNews> findBySportFacilitiesId(Integer sportFacilitiesId);
}