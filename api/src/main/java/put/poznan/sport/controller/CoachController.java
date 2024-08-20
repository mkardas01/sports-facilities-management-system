package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.dto.Coach.CoachUpdate;
import put.poznan.sport.dto.Coach.CreateCoach;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.exception.exceptionClasses.CoachNotFoundException;
import put.poznan.sport.repository.SportFacilityRepository;
import put.poznan.sport.service.CoachImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/coach/")
public class CoachController {
    @Autowired
    private CoachImpl coachService;

    @Autowired
    private SportFacilityRepository sportFacilityRepository;

    @GetMapping("all")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getAllCoaches() {

        return new ResponseEntity<>(coachService.getAllCoaches(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getCoachById(@PathVariable int id) {
        return new ResponseEntity<>(coachService.getCoachById(id), HttpStatus.OK);
    }

    @PostMapping("create")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> createCoach(@RequestBody @Valid CreateCoach coachDTO) {

        Optional<SportFacility> sportFacility = sportFacilityRepository.findById(coachDTO.getSportFacilitiesId());
        coachService.checkIfUserIsManager(sportFacility);

        Coach coach = Coach.builder()
                .name(coachDTO.getName())
                .surname(coachDTO.getSurname())
                .sportFacility(sportFacility.get())
                .imageUrl(coachDTO.getImageUrl())
                .build();

        return new ResponseEntity<>(coachService.createCoach(coach), HttpStatus.OK);
    }

    @PutMapping("update")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> updateCoach(@RequestBody @Valid CoachUpdate coachDTO) {

        Optional<SportFacility> sportFacility = sportFacilityRepository.findById(coachDTO.getSportFacilitiesId());
        coachService.checkIfUserIsManager(sportFacility);

        List<Integer> coachIDs = sportFacility.get().getCoaches()
                .stream()
                .map(Coach::getId)
                .toList();

        if (!coachIDs.contains(coachDTO.getId())) {
            throw new CoachNotFoundException("Nie znaleziono trenera w podanym obiekcie sportowym");
        }

        return new ResponseEntity<>(coachService.updateCoach(coachDTO), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> deleteCoach(@PathVariable int id) {
        return new ResponseEntity<>(coachService.deleteCoach(id), HttpStatus.OK);
    }
}
