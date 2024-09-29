package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.entity.TrainingSessionParticipant;
import put.poznan.sport.entity.TrainingSessionParticipantId;
import put.poznan.sport.service.trainingSessionParticipant.TrainingSessionParticipantService;

@RestController
@RequestMapping("api/training/participant/")
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

    @PostMapping("join/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> createParticipant(@PathVariable int id) {
        return new ResponseEntity<>(trainingSessionParticipantService.joinTraining(id),HttpStatus.OK);
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
