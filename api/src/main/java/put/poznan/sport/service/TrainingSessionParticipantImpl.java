package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.TrainingSessionParticipant;
import put.poznan.sport.entity.TrainingSessionParticipantId;
import put.poznan.sport.repository.TrainingSessionParticipantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingSessionParticipantImpl implements TrainingSessionParticipantService {

    @Autowired
    private TrainingSessionParticipantRepository trainingSessionParticipantRepository;

    public List<TrainingSessionParticipant> getAllParticipants() {trainingSessionParticipantRepository.findAll(); return trainingSessionParticipantRepository.findAll();}

    public TrainingSessionParticipant getParticipantById(TrainingSessionParticipantId id){
        Optional<TrainingSessionParticipant> trainingSessionParticipant = trainingSessionParticipantRepository.findById(id);
        return trainingSessionParticipant.orElse(null);
    }

    public TrainingSessionParticipant createParticipant(TrainingSessionParticipant participant) {return trainingSessionParticipantRepository.save(participant);}

    public TrainingSessionParticipant updateParticipant(TrainingSessionParticipant participant) {return trainingSessionParticipantRepository.save(participant);}

    public boolean deleteParticipant(TrainingSessionParticipantId id) {
        Optional<TrainingSessionParticipant> trainingSessionParticipant = trainingSessionParticipantRepository.findById(id);
        if(trainingSessionParticipant.isPresent()){
            trainingSessionParticipantRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
