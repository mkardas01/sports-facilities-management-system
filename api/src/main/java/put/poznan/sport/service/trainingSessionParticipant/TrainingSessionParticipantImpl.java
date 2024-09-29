package put.poznan.sport.service.trainingSessionParticipant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import put.poznan.sport.entity.*;
import put.poznan.sport.exception.exceptionClasses.TrainingSessionNotFoundException;
import put.poznan.sport.exception.exceptionClasses.TrainingSessionParticipantNotFoundException;
import put.poznan.sport.exception.exceptionClasses.UserIsAlreadyParticipantException;
import put.poznan.sport.exception.exceptionClasses.UserNotFoundException;
import put.poznan.sport.repository.SportFacilityParticipantRepository;
import put.poznan.sport.repository.TrainingSessionParticipantRepository;
import put.poznan.sport.repository.TrainingSessionRepository;
import put.poznan.sport.repository.UserRepository;
import put.poznan.sport.service.user.UserService;

import java.util.List;

@Service
public class TrainingSessionParticipantImpl implements TrainingSessionParticipantService {

    @Autowired
    private TrainingSessionParticipantRepository trainingSessionParticipantRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingSessionRepository trainingSessionRepository;

    @Autowired
    private SportFacilityParticipantRepository sportFacilityParticipantRepository;

    @Override
    public List<TrainingSessionParticipant> getAllParticipants() {
        return trainingSessionParticipantRepository.findAll();
    }

    @Override
    public TrainingSessionParticipant getParticipantById(TrainingSessionParticipantId id) {
        return trainingSessionParticipantRepository.findById(id)
                .orElseThrow(() -> new TrainingSessionParticipantNotFoundException("TrainingSessionParticipant with id " + id + " not found"));
    }

    @Override
    @Transactional
    public TrainingSessionParticipant joinTraining(int id) {
        TrainingSession trainingSession  = trainingSessionRepository.findById(id)
                .orElseThrow(() -> new TrainingSessionNotFoundException("Nie znaleziono treningu"));

        String currentUserEmail = userService.getCurrentUsername();
        User currentUser = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new UserNotFoundException("Nie znaleziono użytkownika"));

        if (isMemberShipRequired(trainingSession.getSportFacility())) {

        }

        if (isUserParticipant(currentUser.getId(), id)) {
            throw new UserIsAlreadyParticipantException("Użytkownik jest juz uczestnikiem");
        }

        TrainingSessionParticipant newParticipant = TrainingSessionParticipant.builder()
                .trainingSessionId(id)
                .userId(currentUser.getId())
                .build();

        return trainingSessionParticipantRepository.save(newParticipant);
    }

    @Override
    public boolean deleteParticipant(TrainingSessionParticipantId id) {
        TrainingSessionParticipant participant = trainingSessionParticipantRepository.findById(id)
                .orElseThrow(() -> new TrainingSessionParticipantNotFoundException("TrainingSessionParticipant with id " + id + " not found"));

        trainingSessionParticipantRepository.deleteById(id);
        return true;
    }

    boolean isUserParticipant(int userId, int trainingSessionId) {
        return trainingSessionParticipantRepository.existsByUserIdAndTrainingSessionId(userId, trainingSessionId);
    }

    boolean isMemberShipRequired(SportFacility facility) {
        return facility.isMembershipRequired();
    }

    void checkUserMemberShip (SportFacility facility) {

    }
}
