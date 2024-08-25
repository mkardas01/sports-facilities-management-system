package put.poznan.sport.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.dto.Rating.CreateRating;
import put.poznan.sport.dto.Rating.Rating;
import put.poznan.sport.entity.User;
import put.poznan.sport.exception.exceptionClasses.UserNotFoundException;
import put.poznan.sport.repository.UserRepository;
import put.poznan.sport.service.rating.RatingService;
import put.poznan.sport.service.user.UserService;

@RestController
@RequestMapping("api/rating/")
public class RatingController {


    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


//
//    @GetMapping("all")
//    @CrossOrigin
//    @ResponseBody
//    public ResponseEntity<?> getAllRatings() {
//
//        return new ResponseEntity<>(ratingService.getAllRatings(), HttpStatus.OK);
//    }
//
//    @GetMapping("{id}")
//    @CrossOrigin
//    @ResponseBody
//    public ResponseEntity<?> getRatingById(@PathVariable int id) {
//
//        return new ResponseEntity<>(ratingService.getRatingById(id), HttpStatus.OK);
//
//    }

    @PostMapping("add")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> createRating(@RequestBody @Valid CreateRating rating) {

        Rating newRating = new Rating();

        User user = userRepository.findByEmail(userService.getCurrentUsername())
                .orElseThrow(() -> new UserNotFoundException("Błąd użytkownika"));

        newRating.setRate(rating.getRate());
        newRating.setAddedBy(user);
        newRating.setObjectId(rating.getObjectId());
        newRating.setObjectType(rating.getObjectType());

        return new ResponseEntity<>(ratingService.createRating(newRating), HttpStatus.OK);

    }
//
//    @PutMapping("update")
//    @CrossOrigin
//    @ResponseBody
//    public ResponseEntity<?> updateRating(@RequestBody Rating rating) {
//
//        return new ResponseEntity<>(ratingService.updateRating(rating), HttpStatus.OK);
//
//    }
//
//    @DeleteMapping("delete/{id}")
//    @CrossOrigin
//    @ResponseBody
//    public ResponseEntity<?> deleteRating(@PathVariable int id) {
//
//        return new ResponseEntity<>(ratingService.deleteRating(id), HttpStatus.OK);
//
//    }
}
