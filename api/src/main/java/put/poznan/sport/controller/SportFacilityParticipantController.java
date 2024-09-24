package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.entity.SportFacilityParticipant;
import put.poznan.sport.entity.SportFacilityParticipantId;
import put.poznan.sport.service.sportFacilityParcticipant.SportFacilityParticipantService;

import java.util.List;

@RestController
@RequestMapping("api/SportFacilityParticipant/")
public class SportFacilityParticipantController {

    @Autowired
    private SportFacilityParticipantService sportFacilityParticipantService;

    @GetMapping("all")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getAllSportFacilityParticipants() {

        return new ResponseEntity<>(sportFacilityParticipantService.getAllFacilityParticipants(), HttpStatus.OK);
    }

    @GetMapping("facilities")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<List<SportFacility>> getSportFacilitiesForLoggedInUser() {
        List<SportFacility> facilities = sportFacilityParticipantService.getSportFacilitiesByLoggedInUser();
        return ResponseEntity.ok(facilities);
    }

    @PostMapping("assign")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> assignUserToFacility(@RequestBody SportFacilityParticipant participant) {
        return new ResponseEntity<>(sportFacilityParticipantService.createSportFacilityParticipant(participant), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getSportFacilityParticipantById(@PathVariable SportFacilityParticipantId id) {
        return new ResponseEntity<>(sportFacilityParticipantService.getSportFacilityParticipantById(id), HttpStatus.OK);
    }

    @PostMapping("create")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> createSportFacilityParticipant(@RequestBody SportFacilityParticipant sportFacilityParticipant) {
        return new ResponseEntity<>(sportFacilityParticipantService.createSportFacilityParticipant(sportFacilityParticipant), HttpStatus.OK);
    }

    @PutMapping("update")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> updateSportFacilityParticipant(@RequestBody SportFacilityParticipant sportFacilityParticipant) {
        return new ResponseEntity<>(sportFacilityParticipantService.updateSportFacilityParticipant(sportFacilityParticipant), HttpStatus.OK);

    }

    @DeleteMapping("delete/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> deleteSportFacilityParticipantById(@PathVariable SportFacilityParticipantId id) {
        return new ResponseEntity<>(sportFacilityParticipantService.deleteSportFacilityParticipant(id), HttpStatus.OK);
    }

}
