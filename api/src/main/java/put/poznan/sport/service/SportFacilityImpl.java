package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.repository.SportFacilityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SportFacilityImpl implements SportFacilityService {

    @Autowired
    private SportFacilityRepository sportFacilityRepository;

    public List<SportFacility> getAllFacilities() {return sportFacilityRepository.findAll();}

    public SportFacility getSportFacilityById(int id) {
        Optional<SportFacility> facility = sportFacilityRepository.findById(id);
        return facility.orElse(null);
    }

    public SportFacility createSportFacility(SportFacility facility) {
        return sportFacilityRepository.save(facility);
    }

    public SportFacility updateSportFacility(SportFacility facility) {
        return sportFacilityRepository.save(facility);
    }
    public boolean deleteSportFacility(int id) {
        Optional<SportFacility> facility = sportFacilityRepository.findById(id);
        if(facility.isPresent()) {
            sportFacilityRepository.delete(facility.get());
            return true;
        }else{
            return false;
        }
    }
}
