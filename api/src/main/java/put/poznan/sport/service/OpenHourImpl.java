package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.openHour.OpenHour;
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
                .orElseThrow(() -> new OpenHourNotFoundException("Nie znaleziono godzin otwarcia dla obiektu o id: " + id));
    }

    @Override
    public OpenHour saveOpenHour(OpenHour openHour) {
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
