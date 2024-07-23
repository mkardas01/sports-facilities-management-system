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
        if (rating.isEmpty()) {
            throw new RatingNotFoundException("Rating with id " + id + " not found");
        }
        return rating.orElse(null);
    }

    public Rating createRating(Rating rating) {
        if (rating.getRate() < 1 || rating.getRate() > 5) {
            throw new InvalidRatingException("Rating value " + rating.getRate() + " is invalid");
        }
        return ratingRepository.save(rating);
    }

    public Rating updateRating(Rating rating) {
        if (!ratingRepository.existsById(rating.getId())) {
            throw new RatingNotFoundException("Rating with id " + rating.getId() + " not found");
        }
        if (rating.getRate() < 1 || rating.getRate() > 5) {
            throw new InvalidRatingException("Rating value " + rating.getRate() + " is invalid");
        }
        return ratingRepository.save(rating);
    }

    public boolean deleteRating(int id) {
        Optional<Rating> RatingOptional = ratingRepository.findById(id);
        if(RatingOptional.isPresent()) {
            ratingRepository.deleteById(id);
            return true;
        }else{
            throw new RatingNotFoundException("Rating with id " + id + " not found");
        }
    }
}
