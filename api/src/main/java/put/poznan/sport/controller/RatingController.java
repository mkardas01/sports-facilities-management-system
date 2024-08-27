package put.poznan.sport.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.dto.Rating.CreateRating;
import put.poznan.sport.dto.Rating.ObjectType;
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

        User user = userRepository.findByEmail(userService.getCurrentUsername())
                .orElseThrow(() -> new UserNotFoundException("Błąd użytkownika"));

        Rating newRating = Rating.builder()
                .addedBy(user)
                .rate(rating.getRate())
                .objectType(rating.getObjectType())
                .objectId(rating.getObjectId())
                .build();

        return new ResponseEntity<>(ratingService.createRating(newRating), HttpStatus.OK);

    }

    @PutMapping("update")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> updateRating(@RequestBody @Valid CreateRating rating) {

        User user = userRepository.findByEmail(userService.getCurrentUsername())
                .orElseThrow(() -> new UserNotFoundException("Błąd użytkownika"));

        Rating newRating = Rating.builder()
                .addedBy(user)
                .rate(rating.getRate())
                .objectType(rating.getObjectType())
                .objectId(rating.getObjectId())
                .build();

        return new ResponseEntity<>(ratingService.updateRating(newRating), HttpStatus.OK);

    }

    @DeleteMapping("delete/{id}")
    @CrossOrigin
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void deleteRating(@RequestParam String type, @PathVariable int id) {
        User user = userRepository.findByEmail(userService.getCurrentUsername())
                .orElseThrow(() -> new UserNotFoundException("Błąd użytkownika"));

        ratingService.deleteRating(user, type, id);
    }
}
