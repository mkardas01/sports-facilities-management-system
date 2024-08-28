package put.poznan.sport.service.trainingSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.TrainingSession;
import put.poznan.sport.repository.TrainingSessionRepository;

import java.util.List;
import java.util.Optional;


public interface TrainingSessionService {

    public List<TrainingSession> getAllSessions() ;

    public TrainingSession getSessionById(int id);

    public TrainingSession createSession(TrainingSession trainingSession) ;

    public TrainingSession updateSession(TrainingSession trainingSession);

    public boolean deleteSession(int id) ;

}

