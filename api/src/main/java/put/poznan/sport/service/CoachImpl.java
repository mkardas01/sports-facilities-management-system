package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.dto.CoachCreateResponse;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.exception.exceptionClasses.CoachNotFoundException;
import put.poznan.sport.repository.CoachRepository;

import java.util.List;

@Service
public class CoachImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

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
    public CoachCreateResponse createCoach(Coach coach) {
        CoachCreateResponse coachCreateResponse = CoachCreateResponse.builder()
                .name(coach.getName())
                .surname(coach.getSurname())
                .imageUrl(coach.getImageUrl())
                .sportFacilitiesId(coach.getSportFacility().getId())
                .build();

        coachRepository.save(coach);

        return coachCreateResponse;
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
