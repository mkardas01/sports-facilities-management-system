package put.poznan.sport.service.sportFacilityNews;


import put.poznan.sport.dto.SportFacilityNews.SportFacilityNewsDTO;
import put.poznan.sport.entity.SportFacilityNews;

import java.util.List;
import java.util.Optional;

public interface SportFacilityNewsService {

    List<SportFacilityNews> getAllFacilityNews();
    Optional<List<SportFacilityNews>> getFacilityNewsBySportFacilityId(int sportFacilityId);

    public SportFacilityNews updateFacilityNews(SportFacilityNewsDTO facilityNews);

    public SportFacilityNews createFacilityNews(SportFacilityNewsDTO news);

    public void deleteFacilityNews(int id);

}