package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.entity.SportEquipment;
import put.poznan.sport.service.SportEquipmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/SportEquipment/")
public class SportEquipmentController {

    @Autowired
    private SportEquipmentService sportEquipmentService;

    @GetMapping("all")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getAllEquipment() {

        return new ResponseEntity<>(sportEquipmentService.getAllEquipments(), HttpStatus.OK);

    }

    @GetMapping("{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getEquipmentById(@PathVariable int id) {

        return new ResponseEntity<>(sportEquipmentService.getEquipmentsById(id), HttpStatus.OK);

    }

    @PostMapping("create")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> createEquipment(@RequestBody SportEquipment sportEquipment) {

        return new ResponseEntity<>(sportEquipmentService.createEquipment(sportEquipment), HttpStatus.OK);
    }

    @PutMapping("update")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> updateEquipment(@RequestBody SportEquipment sportEquipment) {

        return new ResponseEntity<>(sportEquipmentService.updateEquipment(sportEquipment), HttpStatus.OK);

    }

    @DeleteMapping("delete/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> deleteEquipment(@PathVariable int id) {

        return new ResponseEntity<>(sportEquipmentService.deleteEquipment(id), HttpStatus.OK);

    }
}
