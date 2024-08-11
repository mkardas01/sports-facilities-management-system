package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.exception.SportFacilityNotFoundException;
import put.poznan.sport.repository.SportFacilityRepository;

import java.util.List;

@Service
public class SportFacilityImpl implements SportFacilityService {

    @Autowired
    private SportFacilityRepository sportFacilityRepository;

    @Override
    public List<SportFacility> getAllFacilities() {
        return sportFacilityRepository.findAll();
    }

    @Override
    public SportFacility getSportFacilityById(int id) {
        return sportFacilityRepository.findById(id)
                .orElseThrow(() -> new SportFacilityNotFoundException("SportFacility with id " + id + " not found"));
    }

    @Override
    public SportFacility createSportFacility(SportFacility facility) {
        return sportFacilityRepository.save(facility);
    }

    @Override
    public SportFacility updateSportFacility(SportFacility facility) {
        sportFacilityRepository.findById(facility.getId())
                .orElseThrow(() -> new SportFacilityNotFoundException("SportFacility with id " + facility.getId() + " not found"));

        return sportFacilityRepository.save(facility);
    }

    @Override
    public boolean deleteSportFacility(int id) {
        SportFacility facility = sportFacilityRepository.findById(id)
                .orElseThrow(() -> new SportFacilityNotFoundException("SportFacility with id " + id + " not found"));

        sportFacilityRepository.delete(facility);
        return true;
    }
}
