package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.entity.EquipmentOwnership;
import put.poznan.sport.entity.EquipmentOwnershipId;
import put.poznan.sport.service.EquipmentOwnershipService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/EquipmentOwnership/")
public class EquipmentOwnershipController {

    @Autowired
    private EquipmentOwnershipService equipmentOwnershipService;

    @GetMapping("getAllEqOw")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getAllEqOw() {

        return new ResponseEntity<>(equipmentOwnershipService.getAllEqOw(), HttpStatus.OK);
    }

    @GetMapping("getEqOwById/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getEqOwById(@PathVariable("id") EquipmentOwnershipId id) {

        return new ResponseEntity<>(equipmentOwnershipService.getEqOwById(id), HttpStatus.OK);
    }

    @PostMapping("createEqOw")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> createEqOw(@RequestBody EquipmentOwnership equipmentOwnership) {

        return new ResponseEntity<>(equipmentOwnershipService.saveEqOw(equipmentOwnership), HttpStatus.OK);
    }

//    @PutMapping("updateEqOw")
//    @CrossOrigin
//    @ResponseBody
//    public ResponseEntity<?> updateEqOw(@RequestBody EquipmentOwnership equipmentOwnership) {
//
//        return new ResponseEntity<>(equipmentOwnershipService.updateEqOw(equipmentOwnership), HttpStatus.OK);
//    }

    @DeleteMapping("deleteEqOw/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> deleteEqOw(@PathVariable("id") EquipmentOwnershipId id) {

        return new ResponseEntity<>(equipmentOwnershipService.deleteEqOw(id), HttpStatus.OK);
    }
}
