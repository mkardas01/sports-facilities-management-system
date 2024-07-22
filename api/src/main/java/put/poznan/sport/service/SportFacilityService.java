package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.repository.SportFacilityRepository;

import java.util.List;
import java.util.Optional;


public interface SportFacilityService {

    public List<SportFacility> getAllFacilities(); ;

    public SportFacility getSportFacilityById(int id) ;

    public SportFacility createSportFacility(SportFacility sportFacility) ;

    public SportFacility updateSportFacility(SportFacility sportFacility);

    public boolean deleteSportFacility(int id);
}
