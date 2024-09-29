package put.poznan.sport.service.trainingSessionParticipant;

import put.poznan.sport.entity.TrainingSessionParticipant;
import put.poznan.sport.entity.TrainingSessionParticipantId;

import java.util.List;


public interface TrainingSessionParticipantService {

    public List<TrainingSessionParticipant> getAllParticipants(); ;

    public TrainingSessionParticipant getParticipantById(int trainingId) ;

    public TrainingSessionParticipant joinTraining(int id) ;

    public List<TrainingSessionParticipant> getAllParticipant(int trainingId);
    public void leaveTraining(int trainingId);

}
