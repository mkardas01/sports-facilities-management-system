package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.entity.SportFacilityNews;
import put.poznan.sport.service.SportFacilityNewsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/SportFacilityNews/")
public class SportFacilityNewsController {

    @Autowired
    private SportFacilityNewsService sportFacilityNewsService;

    @GetMapping("all")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getAllSportFacilityNews() {

        return new ResponseEntity<>(sportFacilityNewsService.getAllFacilityNews(), HttpStatus.OK);

    }

    @GetMapping("{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getSportFacilityNewsById(@PathVariable("id") Integer id) {

        return new ResponseEntity<>(sportFacilityNewsService.getFacilityNewsById(id),HttpStatus.OK);

    }

    @PostMapping("create")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> createSportFacilityNews(@RequestBody SportFacilityNews news) {

        return new ResponseEntity<>(sportFacilityNewsService.createFacilityNews(news), HttpStatus.CREATED);

    }

    @PutMapping("update")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> updateSportFacilityNews(@RequestBody SportFacilityNews news) {

        return new ResponseEntity<>(sportFacilityNewsService.updateFacilityNews(news), HttpStatus.OK);

    }

    @DeleteMapping("delete/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> deleteSportFacilityNews(@PathVariable("id") Integer id) {

        return new ResponseEntity<>(sportFacilityNewsService.deleteFacilityNews(id), HttpStatus.OK);

    }
}
