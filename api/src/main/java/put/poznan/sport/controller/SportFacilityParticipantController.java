package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.entity.SportFacilityParticipant;
import put.poznan.sport.entity.SportFacilityParticipantId;
import put.poznan.sport.service.SportFacilityParticipantService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/SportFacilityParticipant/")
public class SportFacilityParticipantController {

    @Autowired
    private SportFacilityParticipantService sportFacilityParticipantService;

    @GetMapping("getAllSportFacilityParticipants")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getAllSportFacilityParticipants() {

        return new ResponseEntity<>(sportFacilityParticipantService.getAllFacilityParticipants(), HttpStatus.OK);

    }

    @GetMapping("getSportFacilityParticipantById/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getSportFacilityParticipantById(@PathVariable SportFacilityParticipantId id) {
        return new ResponseEntity<>(sportFacilityParticipantService.getSportFacilityParticipantById(id), HttpStatus.OK);
    }

    @PostMapping("createSportFacilityParticipant")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> createSportFacilityParticipant(@RequestBody SportFacilityParticipant sportFacilityParticipant) {
        return new ResponseEntity<>(sportFacilityParticipantService.createSportFacilityParticipant(sportFacilityParticipant), HttpStatus.OK);
    }

    @PutMapping
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> updateSportFacilityParticipant(@RequestBody SportFacilityParticipant sportFacilityParticipant) {
        return new ResponseEntity<>(sportFacilityParticipantService.updateSportFacilityParticipant(sportFacilityParticipant), HttpStatus.OK);

    }

    @DeleteMapping("deleteSportFacilityParticipantById/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> deleteSportFacilityParticipantById(@PathVariable SportFacilityParticipantId id) {
        return new ResponseEntity<>(sportFacilityParticipantService.deleteSportFacilityParticipant(id), HttpStatus.OK);
    }

}
