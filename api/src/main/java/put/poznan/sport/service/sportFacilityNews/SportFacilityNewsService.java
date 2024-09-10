package put.poznan.sport.service.sportFacilityNews;


import put.poznan.sport.entity.SportFacilityNews;

import java.util.List;
import java.util.Optional;

public interface SportFacilityNewsService {


    public List<SportFacilityNews> getAllFacilityNews();

    public SportFacilityNews getFacilityNewsById(int id);

    List<SportFacilityNews> getFacilityNewsBySportFacilityId(int sportFacilityId);

    public SportFacilityNews updateFacilityNews(SportFacilityNews facilityNews);

    public SportFacilityNews createFacilityNews(SportFacilityNews news);

    public boolean deleteFacilityNews(int id);

}