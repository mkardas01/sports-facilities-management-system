package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.Rating;
import put.poznan.sport.repository.RatingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RatingImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> getAllRatings() {return ratingRepository.findAll();}

    public Rating getRatingById(int id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        return rating.orElse(null);
    }

    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating updateRating(Rating rating) {
       return ratingRepository.save(rating);
    }

    public boolean deleteRating(int id) {
        Optional<Rating> RatingOptional = ratingRepository.findById(id);
        if(RatingOptional.isPresent()) {
            ratingRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
