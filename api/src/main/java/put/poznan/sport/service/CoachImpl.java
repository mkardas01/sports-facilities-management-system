package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.dto.Coach.CoachCreateResponse;
import put.poznan.sport.dto.Coach.CoachUpdate;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.entity.User;
import put.poznan.sport.exception.exceptionClasses.CoachNotFoundException;
import put.poznan.sport.exception.exceptionClasses.InvalidUserException;
import put.poznan.sport.exception.exceptionClasses.SportFacilityNotFoundException;
import put.poznan.sport.repository.CoachRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CoachImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private UserImpl userService;

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

        Coach newCoach = coachRepository.save(coach);

        coachCreateResponse.setId(newCoach.getId());

        return coachCreateResponse;
    }

    @Override
    public CoachUpdate updateCoach(CoachUpdate coach) {
        Coach existingCoach = coachRepository.findById(coach.getId())
                .orElseThrow(() -> new CoachNotFoundException("Wystąpił błąd w czasie wprowadzania zmian u podanego trenera"));

        existingCoach.setName(coach.getName());
        existingCoach.setSurname(coach.getSurname());
        existingCoach.setImageUrl(coach.getImageUrl());

        coachRepository.save(existingCoach);

        return coach;
    }

    @Override
    public boolean deleteCoach(Coach coach) {

        coachRepository.delete(coach);
        return true;
    }
}
