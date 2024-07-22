package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.TrainingSession;
import put.poznan.sport.repository.TrainingSessionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingSessionImpl implements TrainingSessionService {

    @Autowired
    private TrainingSessionRepository trainingSessionRepository;

    public List<TrainingSession> getAllSessions(){return trainingSessionRepository.findAll();}

    public TrainingSession getSessionById(int id){
        Optional<TrainingSession> optionalTrainingSession = trainingSessionRepository.findById(id);
        return optionalTrainingSession.orElse(null);
    }

    public TrainingSession createSession(TrainingSession trainingSession){return trainingSessionRepository.save(trainingSession);}

    public TrainingSession updateSession(TrainingSession trainingSession){return trainingSessionRepository.save(trainingSession);}

    public boolean deleteSession(int id){trainingSessionRepository.deleteById(id);
    Optional<TrainingSession> optionalTrainingSession = trainingSessionRepository.findById(id);
    if(optionalTrainingSession.isPresent()){
        trainingSessionRepository.delete(optionalTrainingSession.get());
        return true;
    }else{
        return false;
    }
    }
}
