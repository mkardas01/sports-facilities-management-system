package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.entity.TrainingSession;
import put.poznan.sport.entity.TrainingSessionParticipant;
import put.poznan.sport.entity.TrainingSessionParticipantId;
import put.poznan.sport.service.TrainingSessionParticipantService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/TrainingSessionParticipant/")
public class TrainingSessionParticipantController {

    @Autowired
    private TrainingSessionParticipantService trainingSessionParticipantService;

    @GetMapping("all")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getAllParticipants() {
        return new ResponseEntity<>(trainingSessionParticipantService.getAllParticipants(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getParticipantById(@PathVariable("id") TrainingSessionParticipantId id) {
        return new ResponseEntity<>(trainingSessionParticipantService.getParticipantById(id),HttpStatus.OK);
    }

    @PostMapping("create")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> createParticipant(@RequestBody TrainingSessionParticipant participant) {
        return new ResponseEntity<>(trainingSessionParticipantService.createParticipant(participant),HttpStatus.OK);
    }

    @PutMapping("update")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> updateParticipant(@RequestBody TrainingSessionParticipant participant) {
        return new ResponseEntity<>(trainingSessionParticipantService.updateParticipant(participant),HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> deleteParticipant(@PathVariable TrainingSessionParticipantId id) {
        return new ResponseEntity<>(trainingSessionParticipantService.deleteParticipant(id),HttpStatus.OK);
    }
}
