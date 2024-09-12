package put.poznan.sport.service.sportFacilityParcticipant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.entity.SportFacilityParticipant;
import put.poznan.sport.entity.SportFacilityParticipantId;
import put.poznan.sport.repository.SportFacilityParticipantRepository;

import java.util.List;
import java.util.Optional;

public interface SportFacilityParticipantService {


    public List<SportFacilityParticipant> getAllFacilityParticipants();

    public SportFacilityParticipant getSportFacilityParticipantById(SportFacilityParticipantId id);

    public SportFacilityParticipant createSportFacilityParticipant(SportFacilityParticipant sportFacilityParticipant);

    public SportFacilityParticipant updateSportFacilityParticipant(SportFacilityParticipant sportFacilityParticipant);

    public List<SportFacility> getFacilitiesByUserId(Integer userId);

    List<Integer> getSportFacilityIdsByUserId(Integer userId);

    public boolean deleteSportFacilityParticipant(SportFacilityParticipantId id);
}
