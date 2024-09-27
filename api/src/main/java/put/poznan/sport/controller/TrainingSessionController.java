package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.dto.TraningSession.TrainingSessionDTO;
import put.poznan.sport.entity.TrainingSession;
import put.poznan.sport.exception.exceptionClasses.TrainingSessionNotFoundException;
import put.poznan.sport.service.trainingSession.TrainingSessionService;

import java.util.List;

@RestController
@RequestMapping("api/training/")
public class TrainingSessionController {

    @Autowired
    private TrainingSessionService trainingSessionService;

    @GetMapping("all")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getAllSessions() {
        List<TrainingSession> allTrainingSession = trainingSessionService.getAllSessions()
                .orElseThrow(()-> new TrainingSessionNotFoundException("Nie znaleziono treningów"));
        return new ResponseEntity<>(allTrainingSession, HttpStatus.OK);
    }

    @GetMapping("facility/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getFacilityTrainings(int id) {
        List<TrainingSession> allTrainingSession = trainingSessionService.getAllSessions()
                .orElseThrow(()-> new TrainingSessionNotFoundException("Nie znaleziono treningów"));
        return new ResponseEntity<>(allTrainingSession, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getSessionById(@PathVariable("id") int id) {
        return new ResponseEntity<>(trainingSessionService.getSessionById(id), HttpStatus.OK);
    }

    @PostMapping("create")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> createSession(@RequestBody TrainingSessionDTO trainingSession) {
        return new ResponseEntity<>(trainingSessionService.createSession(trainingSession), HttpStatus.OK);
    }

    @PutMapping("update")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> updateSession(@RequestBody TrainingSessionDTO trainingSession) {
        return new ResponseEntity<>(trainingSessionService.updateSession(trainingSession), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @CrossOrigin
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void deleteSession(@PathVariable int id) {
        trainingSessionService.deleteSession(id);
    }

}
