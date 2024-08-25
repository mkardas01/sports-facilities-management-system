package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.entity.OpenHour;
import put.poznan.sport.service.OpenHourService;

@RestController
@RequestMapping("api/OpenHour/")
public class OpenHourController {

    @Autowired
    private OpenHourService openHourService;

    @GetMapping("all")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getAllOpenHours(){

        return new ResponseEntity<>(openHourService.getAllOpenHours(), HttpStatus.OK);

    }

    @GetMapping("{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getOpenHourById(@PathVariable("id") int id){

        return new ResponseEntity<>(openHourService.getOpenHourById(id), HttpStatus.OK);

    }


    @PostMapping("create")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> createOpenHour(@RequestBody OpenHour openHour){

        return new ResponseEntity<>(openHourService.saveOpenHour(openHour), HttpStatus.OK);

    }

    @PutMapping("update")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> updateOpenHour(@RequestBody OpenHour openHour){

        return new ResponseEntity<>(openHourService.updateOpenHour(openHour), HttpStatus.OK);

    }

    @DeleteMapping("delete/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> deleteOpenHour(@PathVariable("id") int id){

        return new ResponseEntity<>(openHourService.deleteOpenHour(id), HttpStatus.OK);

    }
}
