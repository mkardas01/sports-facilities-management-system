package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.SportFacilityParticipant;
import put.poznan.sport.entity.SportFacilityParticipantId;
import put.poznan.sport.repository.SportFacilityParticipantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SportFacilityParticipantImpl implements SportFacilityParticipantService {

    @Autowired
    private SportFacilityParticipantRepository sportFacilityParticipantRepository;

    public List<SportFacilityParticipant> getAllFacilityParticipants() {return sportFacilityParticipantRepository.findAll();
    }

    public SportFacilityParticipant getSportFacilityParticipantById(SportFacilityParticipantId id) {
        Optional<SportFacilityParticipant> optional = sportFacilityParticipantRepository.findById(id);
        return optional.orElse(null);
    }

    public SportFacilityParticipant createSportFacilityParticipant(SportFacilityParticipant participant) {return sportFacilityParticipantRepository.save(participant);}

    public SportFacilityParticipant updateSportFacilityParticipant(SportFacilityParticipant participant) {
        return sportFacilityParticipantRepository.save(participant);}

    public boolean deleteSportFacilityParticipant(SportFacilityParticipantId id) {
        Optional<SportFacilityParticipant> optional = sportFacilityParticipantRepository.findById(id);
        if(optional.isPresent()) {
            sportFacilityParticipantRepository.delete(optional.get());
            return true;
        }else{
            return false;
        }
    }
}
