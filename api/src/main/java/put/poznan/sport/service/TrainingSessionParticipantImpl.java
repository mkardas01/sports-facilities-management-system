package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.TrainingSessionParticipant;
import put.poznan.sport.entity.TrainingSessionParticipantId;
import put.poznan.sport.exception.exceptionClasses.TrainingSessionParticipantNotFoundException;
import put.poznan.sport.repository.TrainingSessionParticipantRepository;

import java.util.List;

@Service
public class TrainingSessionParticipantImpl implements TrainingSessionParticipantService {

    @Autowired
    private TrainingSessionParticipantRepository trainingSessionParticipantRepository;

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
    public TrainingSessionParticipant createParticipant(TrainingSessionParticipant participant) {
        return trainingSessionParticipantRepository.save(participant);
    }

    @Override
    public TrainingSessionParticipant updateParticipant(TrainingSessionParticipant participant) {
        trainingSessionParticipantRepository.findById(participant.getId())
                .orElseThrow(() -> new TrainingSessionParticipantNotFoundException("TrainingSessionParticipant with id " + participant.getId() + " not found"));

        return trainingSessionParticipantRepository.save(participant);
    }

    @Override
    public boolean deleteParticipant(TrainingSessionParticipantId id) {
        TrainingSessionParticipant participant = trainingSessionParticipantRepository.findById(id)
                .orElseThrow(() -> new TrainingSessionParticipantNotFoundException("TrainingSessionParticipant with id " + id + " not found"));

        trainingSessionParticipantRepository.deleteById(id);
        return true;
    }
}
