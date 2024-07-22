package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.SportFacilityNews;
import put.poznan.sport.repository.SportFacilityNewsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SportFacilityNewsImpl implements SportFacilityNewsService{

    @Autowired
    private SportFacilityNewsRepository SportFacilityNewsrepository;

    public List<SportFacilityNews> getAllFacilityNews() { return SportFacilityNewsrepository.findAll(); }

    public SportFacilityNews getFacilityNewsById(int id) {

        Optional<SportFacilityNews> optional = SportFacilityNewsrepository.findById(id);
        return optional.orElse(null);

    }

    public SportFacilityNews createFacilityNews(SportFacilityNews facilityNews) { return SportFacilityNewsrepository.save(facilityNews); }

    public SportFacilityNews updateFacilityNews(SportFacilityNews facilityNews) {

        return SportFacilityNewsrepository.save(facilityNews);

    }

    public boolean deleteFacilityNews(int id) {
        Optional<SportFacilityNews> optional = SportFacilityNewsrepository.findById(id);
        if(optional.isPresent()) {
            SportFacilityNewsrepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
