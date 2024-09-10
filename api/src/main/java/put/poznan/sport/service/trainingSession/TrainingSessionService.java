package put.poznan.sport.service.trainingSession;

import put.poznan.sport.entity.TrainingSession;

import java.util.List;


public interface TrainingSessionService {

    public List<TrainingSession> getAllSessions() ;

    public TrainingSession getSessionById(int id);

    public TrainingSession createSession(TrainingSession trainingSession) ;

    public TrainingSession updateSession(TrainingSession trainingSession);

    List<TrainingSession> getTrainingSessionsBySportFacilityId(int sportFacilityId);

    public boolean deleteSession(int id) ;

}

