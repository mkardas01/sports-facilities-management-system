package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.TrainingSession;
import put.poznan.sport.exception.exceptionClasses.TrainingSessionNotFoundException;
import put.poznan.sport.repository.TrainingSessionRepository;

import java.util.List;

@Service
public class TrainingSessionImpl implements TrainingSessionService {

    @Autowired
    private TrainingSessionRepository trainingSessionRepository;

    @Override
    public List<TrainingSession> getAllSessions() {
        return trainingSessionRepository.findAll();
    }

    @Override
    public TrainingSession getSessionById(int id) {
        return trainingSessionRepository.findById(id)
                .orElseThrow(() -> new TrainingSessionNotFoundException("TrainingSession with id " + id + " not found"));
    }

    @Override
    public TrainingSession createSession(TrainingSession trainingSession) {
        return trainingSessionRepository.save(trainingSession);
    }

    @Override
    public TrainingSession updateSession(TrainingSession trainingSession) {
        trainingSessionRepository.findById(trainingSession.getId())
                .orElseThrow(() -> new TrainingSessionNotFoundException("TrainingSession with id " + trainingSession.getId() + " not found"));

        return trainingSessionRepository.save(trainingSession);
    }

    @Override
    public boolean deleteSession(int id) {
        TrainingSession trainingSession = trainingSessionRepository.findById(id)
                .orElseThrow(() -> new TrainingSessionNotFoundException("TrainingSession with id " + id + " not found"));

        trainingSessionRepository.delete(trainingSession);
        return true;
    }
}
