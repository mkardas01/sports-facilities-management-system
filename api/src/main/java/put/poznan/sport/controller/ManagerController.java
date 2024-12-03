package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.dto.Manager.ManagerDTO;
import put.poznan.sport.service.sportFacility.SportFacilityService;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    private SportFacilityService sportFacilityService;

    @PostMapping("/add")
    public ResponseEntity<?> addManager(@RequestBody ManagerDTO managerDTO) {
        return new ResponseEntity<>(sportFacilityService.addManager(managerDTO), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public void deleteManager(@PathVariable int id) {

    }

}
