package put.poznan.sport.service.sportFacilityParcticipant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.SportFacilityParticipant;
import put.poznan.sport.entity.SportFacilityParticipantId;
import put.poznan.sport.exception.exceptionClasses.SportFacilityParticipantNotFoundException;
import put.poznan.sport.repository.SportFacilityParticipantRepository;

import java.util.List;

@Service
public class SportFacilityParticipantImpl implements SportFacilityParticipantService {

    @Autowired
    private SportFacilityParticipantRepository sportFacilityParticipantRepository;

    @Override
    public List<SportFacilityParticipant> getAllFacilityParticipants() {
        return sportFacilityParticipantRepository.findAll();
    }

    @Override
    public SportFacilityParticipant getSportFacilityParticipantById(SportFacilityParticipantId id) {
        return sportFacilityParticipantRepository.findById(id)
                .orElseThrow(() -> new SportFacilityParticipantNotFoundException("SportFacilityParticipant with id " + id + " not found"));
    }

    @Override
    public SportFacilityParticipant createSportFacilityParticipant(SportFacilityParticipant participant) {
        return sportFacilityParticipantRepository.save(participant);
    }

    @Override
    public SportFacilityParticipant updateSportFacilityParticipant(SportFacilityParticipant participant) {
        SportFacilityParticipantId user = new SportFacilityParticipantId(participant.getUserId(), participant.getSportFacilitiesId());

        sportFacilityParticipantRepository.findById(user)
                .orElseThrow(() -> new SportFacilityParticipantNotFoundException("SportFacilityParticipant with id " + user.getUserId() + " not found"));

        return sportFacilityParticipantRepository.save(participant);
    }

    @Override
    public boolean deleteSportFacilityParticipant(SportFacilityParticipantId id) {
        SportFacilityParticipant participant = sportFacilityParticipantRepository.findById(id)
                .orElseThrow(() -> new SportFacilityParticipantNotFoundException("SportFacilityParticipant with id " + id + " not found"));

        sportFacilityParticipantRepository.delete(participant);
        return true;
    }
}
