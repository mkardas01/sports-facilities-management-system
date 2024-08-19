package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.exception.exceptionClasses.CoachNotFoundException;
import put.poznan.sport.exception.exceptionClasses.SportFacilityNotFoundException;
import put.poznan.sport.repository.CoachRepository;
import put.poznan.sport.repository.SportFacilityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CoachImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private SportFacilityRepository sportFacilityRepository;

    @Override
    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    @Override
    public Coach getCoachById(int id) {
        return coachRepository.findById(id)
                .orElseThrow(() -> new CoachNotFoundException("Coach with id " + id + " not found"));
    }

    @Override
    public Coach createCoach(Coach coach) {

        Optional<SportFacility> sportFacility = sportFacilityRepository.findById(coach.getSportFacilitiesId());
        if (sportFacility.isEmpty()) {
            throw new SportFacilityNotFoundException("Nie znaleziono obiektu sportowego");
        }

        return coachRepository.save(coach);
    }

    @Override
    public Coach updateCoach(Coach coach) {
        coachRepository.findById(coach.getId())
                .orElseThrow(() -> new CoachNotFoundException("Coach with id " + coach.getId() + " not found"));

        return coachRepository.save(coach);
    }

    @Override
    public boolean deleteCoach(int id) {
        Coach coach = coachRepository.findById(id)
                .orElseThrow(() -> new CoachNotFoundException("Coach with id " + id + " not found"));

        coachRepository.deleteById(id);
        return true;
    }
}
