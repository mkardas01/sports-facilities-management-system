package put.poznan.sport.service;

import put.poznan.sport.dto.OpeHour.CreateOpenHour;
import put.poznan.sport.entity.openHour.OpenHour;

import java.util.List;

public interface OpenHourService {


    public List<OpenHour> getAllOpenHours();

    public OpenHour getOpenHourById(int id);

    public OpenHour saveOpenHour(OpenHour openHour);

    public boolean deleteOpenHour(int id);
}