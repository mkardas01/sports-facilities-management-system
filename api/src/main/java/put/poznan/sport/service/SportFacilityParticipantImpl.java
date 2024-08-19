package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.SportFacilityParticipant;
import put.poznan.sport.entity.SportFacilityParticipantId;
import put.poznan.sport.exception.SportFacilityParticipantNotFoundException;
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
        sportFacilityParticipantRepository.findById(participant.getId())
                .orElseThrow(() -> new SportFacilityParticipantNotFoundException("SportFacilityParticipant with id " + participant.getId() + " not found"));

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
