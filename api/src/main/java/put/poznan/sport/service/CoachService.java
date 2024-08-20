package put.poznan.sport.service;

import put.poznan.sport.dto.CoachCreateResponse;
import put.poznan.sport.dto.CreateCoach;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.entity.SportFacility;

import java.util.List;
import java.util.Optional;

public interface CoachService {

    public List<Coach> getAllCoaches();

    public Coach getCoachById(int id);

    public Optional<SportFacility> checkIfUserIsManager(CreateCoach coachDTO);

    public CoachCreateResponse createCoach(Coach coach);

    public Coach updateCoach(Coach coach);

    public boolean deleteCoach(int id);
}
