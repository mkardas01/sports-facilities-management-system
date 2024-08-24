package put.poznan.sport.service;

import put.poznan.sport.dto.Coach.CoachCreateResponse;
import put.poznan.sport.dto.Coach.CoachUpdate;
import put.poznan.sport.dto.Coach.CreateCoach;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.entity.SportFacility;

import java.util.List;
import java.util.Optional;

public interface CoachService {

    public List<Coach> getAllCoaches();

    public Coach getCoachById(int id);

    public CoachCreateResponse createCoach(Coach coach);

    public CoachUpdate updateCoach(CoachUpdate coach, Coach existingCoach);

    public void deleteCoach(Coach coach);
}
