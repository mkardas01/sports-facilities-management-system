package put.poznan.sport.service.sportFacility;

import jakarta.transaction.Transactional;
import put.poznan.sport.dto.Manager.ManagerDTO;
import put.poznan.sport.dto.SportFacility.SportFacilityDTO;
import put.poznan.sport.entity.sportFacility.SportFacility;

import java.util.List;


public interface SportFacilityService {

    public List<SportFacility> getAllFacilities();

    public SportFacility getSportFacilityById(int id) ;

    public SportFacility createSportFacility(SportFacilityDTO sportFacility) ;

    public SportFacility updateSportFacility(SportFacilityDTO sportFacility);

    public void deleteSportFacility(int id);

    @Transactional
    SportFacility addManager(ManagerDTO managerDTO);

    @Transactional
    SportFacility deleteManager(ManagerDTO managerDTO);
}
