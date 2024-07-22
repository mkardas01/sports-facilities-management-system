package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.OpenHour;
import put.poznan.sport.repository.OpenHourRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OpenHourImpl implements OpenHourService {

    @Autowired
    private OpenHourRepository openHourRepository;

    public List<OpenHour> getAllOpenHours(){
        return openHourRepository.findAll();
    }

    public OpenHour getOpenHourById(int id){
        Optional<OpenHour> openHour = openHourRepository.findById(id);
        return openHour.orElse(null);
    }

    public OpenHour saveOpenHour(OpenHour openHour){
        return openHourRepository.save(openHour);
    }

    public OpenHour updateOpenHour(OpenHour openHour){
        return openHourRepository.save(openHour);
    }

    public boolean deleteOpenHour(int id){
        Optional<OpenHour> openHour = openHourRepository.findById(id);
        if(openHour.isPresent()){
            openHourRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
