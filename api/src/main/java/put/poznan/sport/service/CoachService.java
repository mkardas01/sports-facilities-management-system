package put.poznan.sport.service;

import put.poznan.sport.entity.Coach;

import java.util.List;

public interface CoachService {

    public List<Coach> getAllCoaches();

    public Coach getCoachById(int id);

    public Coach createCoach(Coach coach);

    public Coach updateCoach(Coach coach);

    public boolean deleteCoach(int id);
}
