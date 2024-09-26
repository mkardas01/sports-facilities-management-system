package put.poznan.sport.service.sportFacilityParcticipant;


import put.poznan.sport.dto.SportFacilityParticipant.SportFacilityParticipantDTO;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.entity.SportFacilityParticipant;
import put.poznan.sport.entity.User;

import java.util.List;

public interface SportFacilityParticipantService {


    public List<SportFacilityParticipant> getAllFacilityParticipants();

    public List<User> getUsersBySportFacility(Integer facilityID);
    public SportFacilityParticipant createSportFacilityParticipant(SportFacilityParticipantDTO sportFacilityParticipant);

    public SportFacilityParticipant changeParticipantStatus(SportFacilityParticipantDTO sportFacilityParticipant);

    List<SportFacility> getSportFacilitiesByCurrentUser();

    public void deleteSportFacilityParticipant( Integer userId, Integer facilityId);
}
