package put.poznan.sport.service.sportFacilityNews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.SportFacilityNews;
import put.poznan.sport.exception.exceptionClasses.SportFacilityNewsNotFoundException;
import put.poznan.sport.repository.SportFacilityNewsRepository;

import java.util.List;

@Service
public class SportFacilityNewsImpl implements SportFacilityNewsService {

    @Autowired
    private SportFacilityNewsRepository sportFacilityNewsRepository;

    @Override
    public List<SportFacilityNews> getAllFacilityNews() {
        return sportFacilityNewsRepository.findAll();
    }

    @Override
    public SportFacilityNews getFacilityNewsById(int id) {
        return sportFacilityNewsRepository.findById(id)
                .orElseThrow(() -> new SportFacilityNewsNotFoundException("SportFacilityNews with id " + id + " not found"));
    }

    @Override
    public SportFacilityNews createFacilityNews(SportFacilityNews facilityNews) {
        return sportFacilityNewsRepository.save(facilityNews);
    }

    @Override
    public SportFacilityNews updateFacilityNews(SportFacilityNews facilityNews) {
        sportFacilityNewsRepository.findById(facilityNews.getId())
                .orElseThrow(() -> new SportFacilityNewsNotFoundException("SportFacilityNews with id " + facilityNews.getId() + " not found"));

        return sportFacilityNewsRepository.save(facilityNews);
    }

    @Override
    public boolean deleteFacilityNews(int id) {
        SportFacilityNews facilityNews = sportFacilityNewsRepository.findById(id)
                .orElseThrow(() -> new SportFacilityNewsNotFoundException("SportFacilityNews with id " + id + " not found"));

        sportFacilityNewsRepository.deleteById(id);
        return true;
    }
}
