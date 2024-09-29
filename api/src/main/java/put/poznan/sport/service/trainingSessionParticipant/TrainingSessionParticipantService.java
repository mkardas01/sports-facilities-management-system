package put.poznan.sport.service.trainingSessionParticipant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.TrainingSessionParticipant;
import put.poznan.sport.entity.TrainingSessionParticipantId;
import put.poznan.sport.repository.TrainingSessionParticipantRepository;

import java.util.List;
import java.util.Optional;


public interface TrainingSessionParticipantService {

    public List<TrainingSessionParticipant> getAllParticipants(); ;

    public TrainingSessionParticipant getParticipantById(int userId, int trainingId) ;

    public TrainingSessionParticipant joinTraining(int id) ;
    public boolean deleteParticipant(TrainingSessionParticipantId id);

}
