package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.entity.Rating;
import put.poznan.sport.service.RatingService;

@RestController
@RequestMapping("api/Rating/")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("all")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getAllRatings() {

        return new ResponseEntity<>(ratingService.getAllRatings(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> getRatingById(@PathVariable int id) {

        return new ResponseEntity<>(ratingService.getRatingById(id), HttpStatus.OK);

    }

    @PostMapping("create")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> createRating(@RequestBody Rating rating) {

        return new ResponseEntity<>(ratingService.createRating(rating), HttpStatus.OK);

    }

    @PutMapping("update")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> updateRating(@RequestBody Rating rating) {

        return new ResponseEntity<>(ratingService.updateRating(rating), HttpStatus.OK);

    }

    @DeleteMapping("delete/{id}")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> deleteRating(@PathVariable int id) {

        return new ResponseEntity<>(ratingService.deleteRating(id), HttpStatus.OK);

    }
}
