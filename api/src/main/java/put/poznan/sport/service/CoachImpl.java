package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.dto.CoachCreateResponse;
import put.poznan.sport.dto.CreateCoach;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.entity.User;
import put.poznan.sport.exception.exceptionClasses.CoachNotFoundException;
import put.poznan.sport.exception.exceptionClasses.InvalidUserException;
import put.poznan.sport.exception.exceptionClasses.SportFacilityNotFoundException;
import put.poznan.sport.repository.CoachRepository;
import put.poznan.sport.repository.SportFacilityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CoachImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private SportFacilityRepository sportFacilityRepository;

    @Autowired
    private UserImpl userImplementation;

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
    public Optional<SportFacility> checkIfUserIsManager(CreateCoach coachDTO){
        Optional<SportFacility> sportFacility = sportFacilityRepository.findById(coachDTO.getSportFacilitiesId());

        if (sportFacility.isEmpty()) {
            throw new SportFacilityNotFoundException("Nie znaleziono podanego obiektu sportowego w bazie danych");
        }

        String currentUser = userImplementation.getCurrentUsername();

        List<String> managerUserNames = sportFacility.get()
                .getManagers()
                .stream()
                .map(User::getUsername)
                .toList();

        if(!managerUserNames.contains(currentUser)){
            throw new InvalidUserException("Nie możesz zarządzać tym obiektem z poziomu tego konta");
        }

        return sportFacility;
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
                .orElseThrow(() -> new CoachNotFoundException("Wystąpił błąd w czasie wprowadzania zmian u podanego trenera"));

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
