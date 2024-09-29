package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.service.sportFacility.SportFacilityService;

@RestController
@RequestMapping("api/SportFacility/")
@CrossOrigin(origins = "http://localhost:5173")
public class SportFacilityController {

    @Autowired
    private SportFacilityService sportFacilityService;

    @GetMapping("all")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getAllSportFacilities() {

        return new ResponseEntity<>(sportFacilityService.getAllFacilities(), HttpStatus.OK);

    }

    @GetMapping("{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getSportFacilityById(@PathVariable("id") int id) {

        return new ResponseEntity<>(sportFacilityService.getSportFacilityById(id), HttpStatus.OK);

    }

    @PostMapping("create")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> createSportFacility(@RequestBody SportFacility sportFacility) {
        return new ResponseEntity<>(sportFacilityService.createSportFacility(sportFacility), HttpStatus.OK);
    }

    @PutMapping("update")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> updateSportFacility(@RequestBody SportFacility sportFacility) {
        return new ResponseEntity<>(sportFacilityService.updateSportFacility(sportFacility), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> deleteSportFacility(@PathVariable("id") int id) {
        return new ResponseEntity<>(sportFacilityService.deleteSportFacility(id), HttpStatus.OK);
    }
}
