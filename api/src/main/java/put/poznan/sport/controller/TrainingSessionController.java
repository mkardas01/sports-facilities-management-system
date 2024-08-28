package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.entity.TrainingSession;
import put.poznan.sport.service.trainingSession.TrainingSessionService;

@RestController
@RequestMapping("api/TrainingSession/")
public class TrainingSessionController {

    @Autowired
    private TrainingSessionService trainingSessionService;

    @GetMapping("all")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getAllSessions() {
        return new ResponseEntity<>(trainingSessionService.getAllSessions(), HttpStatus.OK);
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
    public ResponseEntity<?> createSession(@RequestBody TrainingSession trainingSession) {
        return new ResponseEntity<>(trainingSessionService.createSession(trainingSession), HttpStatus.OK);
    }

    @PutMapping("update")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> updateSession(@RequestBody TrainingSession trainingSession) {
        return new ResponseEntity<>(trainingSessionService.updateSession(trainingSession), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> deleteSession(@PathVariable int id) {
        return new ResponseEntity<>(trainingSessionService.deleteSession(id),HttpStatus.OK);
    }

}
