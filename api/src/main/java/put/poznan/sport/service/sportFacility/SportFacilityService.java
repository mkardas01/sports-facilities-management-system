package put.poznan.sport.service.sportFacility;

import put.poznan.sport.entity.SportFacility;

import java.util.List;


public interface SportFacilityService {

    public List<SportFacility> getAllFacilities(); ;

    public SportFacility getSportFacilityById(int id) ;

    public SportFacility createSportFacility(SportFacility sportFacility) ;

    public SportFacility updateSportFacility(SportFacility sportFacility);

    public boolean deleteSportFacility(int id);
}
