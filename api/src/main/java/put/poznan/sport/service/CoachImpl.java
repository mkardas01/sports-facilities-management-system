package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.repository.CoachRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CoachImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    public List<Coach> getAllCoaches(){
        return coachRepository.findAll();
    }

    public Coach getCoachById(int id){

        Optional<Coach> coach = coachRepository.findById(id);
        return coach.orElse(null);

    }

    public Coach createCoach(Coach coach){
        return coachRepository.save(coach);
    }

    public Coach updateCoach(Coach coach) {

        return coachRepository.save(coach);

    }

    public boolean deleteCoach(int id){
        Optional<Coach> coachOptional = coachRepository.findById(id);
        if(coachOptional.isPresent()){
        coachRepository.deleteById(id);
        return true;
        }else{
            return false;
        }
    }
}
