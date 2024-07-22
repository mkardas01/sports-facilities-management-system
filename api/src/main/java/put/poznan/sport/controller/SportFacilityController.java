package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.service.SportFacilityService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/SportFacility/")
public class SportFacilityController {

    @Autowired
    private SportFacilityService sportFacilityService;

    @GetMapping("getAllSportFacilities")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getAllSportFacilities() {

        return new ResponseEntity<>(sportFacilityService.getAllFacilities(), HttpStatus.OK);

    }

    @GetMapping("getSportFacilityById/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getSportFacilityById(@PathVariable("id") int id) {

        return new ResponseEntity<>(sportFacilityService.getSportFacilityById(id), HttpStatus.OK);

    }

    @PostMapping("createSportFacility")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> createSportFacility(@RequestBody SportFacility sportFacility) {
        return new ResponseEntity<>(sportFacilityService.createSportFacility(sportFacility), HttpStatus.OK);
    }

    @PutMapping("updateSportFacility")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> updateSportFacility(@RequestBody SportFacility sportFacility) {
        return new ResponseEntity<>(sportFacilityService.updateSportFacility(sportFacility), HttpStatus.OK);
    }

    @DeleteMapping("deleteSportFacility/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> deleteSportFacility(@PathVariable("id") int id) {
        return new ResponseEntity<>(sportFacilityService.deleteSportFacility(id), HttpStatus.OK);
    }
}
