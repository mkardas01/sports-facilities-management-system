package put.poznan.sport.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.dto.CreateCoach;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.entity.User;
import put.poznan.sport.exception.exceptionClasses.InvalidUserException;
import put.poznan.sport.exception.exceptionClasses.SportFacilityNotFoundException;
import put.poznan.sport.repository.SportFacilityRepository;
import put.poznan.sport.service.CoachImpl;
import put.poznan.sport.service.UserImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/Coach/")
public class CoachController {
    @Autowired
    private CoachImpl coachService;

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

        Optional<SportFacility> sportFacility = coachService.checkIfUserIsManager(coachDTO);

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
    public ResponseEntity<?> updateCoach(@RequestBody Coach coach) {

        return new ResponseEntity<>(coachService.updateCoach(coach), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> deleteCoach(@PathVariable int id) {
        return new ResponseEntity<>(coachService.deleteCoach(id), HttpStatus.OK);
    }
}
