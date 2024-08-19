package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.OpenHour;
import put.poznan.sport.repository.OpenHourRepository;

import java.util.List;
import java.util.Optional;

public interface OpenHourService {


    public List<OpenHour> getAllOpenHours();

    public OpenHour getOpenHourById(int id);

    public OpenHour updateOpenHour(OpenHour openHour);

    public OpenHour saveOpenHour(OpenHour openHour);

    public boolean deleteOpenHour(int id);
}