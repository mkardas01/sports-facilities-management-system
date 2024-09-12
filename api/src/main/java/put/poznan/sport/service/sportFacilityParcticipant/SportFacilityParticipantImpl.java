package put.poznan.sport.service.sportFacilityParcticipant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.entity.SportFacilityParticipant;
import put.poznan.sport.entity.SportFacilityParticipantId;
import put.poznan.sport.entity.User;
import put.poznan.sport.exception.exceptionClasses.SportFacilityParticipantNotFoundException;
import put.poznan.sport.repository.SportFacilityParticipantRepository;
import put.poznan.sport.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SportFacilityParticipantImpl implements SportFacilityParticipantService {

    @Autowired
    private SportFacilityParticipantRepository sportFacilityParticipantRepository;

    @Override
    public List<SportFacilityParticipant> getAllFacilityParticipants() {
        return sportFacilityParticipantRepository.findAll();
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<SportFacility> getFacilitiesByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return sportFacilityParticipantRepository.findAllByUser(user)
                .stream()
                .map(SportFacilityParticipant::getSportFacility)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getSportFacilityIdsByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Pobieramy ID obiektów sportowych, na które użytkownik jest zapisany
        return sportFacilityParticipantRepository.findAllByUser(user)
                .stream()
                .map(participant -> participant.getSportFacility().getId())
                .collect(Collectors.toList());
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
