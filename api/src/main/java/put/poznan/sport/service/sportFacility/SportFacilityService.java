package put.poznan.sport.service.sportFacility;

import put.poznan.sport.dto.SportFacility.SportFacilityDTO;
import put.poznan.sport.entity.SportFacility;

import java.util.List;


public interface SportFacilityService {

    public List<SportFacility> getAllFacilities();

    public SportFacility getSportFacilityById(int id) ;

    public SportFacility createSportFacility(SportFacilityDTO sportFacility) ;

    public SportFacility updateSportFacility(SportFacilityDTO sportFacility);

    public void deleteSportFacility(int id);
}
