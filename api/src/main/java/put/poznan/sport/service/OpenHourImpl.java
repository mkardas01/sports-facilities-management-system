package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.OpenHour;
import put.poznan.sport.exception.exceptionClasses.OpenHourNotFoundException;
import put.poznan.sport.repository.OpenHourRepository;

import java.util.List;

@Service
public class OpenHourImpl implements OpenHourService {

    @Autowired
    private OpenHourRepository openHourRepository;

    @Override
    public List<OpenHour> getAllOpenHours() {
        return openHourRepository.findAll();
    }

    @Override
    public OpenHour getOpenHourById(int id) {
        return openHourRepository.findById(id)
                .orElseThrow(() -> new OpenHourNotFoundException("OpenHour with id " + id + " not found"));
    }

    @Override
    public OpenHour saveOpenHour(OpenHour openHour) {
        return openHourRepository.save(openHour);
    }

    @Override
    public OpenHour updateOpenHour(OpenHour openHour) {
        openHourRepository.findById(openHour.getId())
                .orElseThrow(() -> new OpenHourNotFoundException("OpenHour with id " + openHour.getId() + " not found"));

        return openHourRepository.save(openHour);
    }

    @Override
    public boolean deleteOpenHour(int id) {
        OpenHour openHour = openHourRepository.findById(id)
                .orElseThrow(() -> new OpenHourNotFoundException("OpenHour with id " + id + " not found"));

        openHourRepository.deleteById(id);
        return true;
    }
}
