package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.Rating;
import put.poznan.sport.exception.exceptionClasses.InvalidRatingException;
import put.poznan.sport.exception.exceptionClasses.RatingNotFoundException;
import put.poznan.sport.repository.RatingRepository;

import java.util.List;

@Service
public class RatingImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingById(int id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new RatingNotFoundException("Rating with id " + id + " not found"));
    }

    @Override
    public Rating createRating(Rating rating) {
        if (rating.getRate() < 1 || rating.getRate() > 5) {
            throw new InvalidRatingException("Rating value " + rating.getRate() + " is invalid");
        }
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Rating rating) {
        ratingRepository.findById(rating.getId())
                .orElseThrow(() -> new RatingNotFoundException("Rating with id " + rating.getId() + " not found"));

        if (rating.getRate() < 1 || rating.getRate() > 5) {
            throw new InvalidRatingException("Rating value " + rating.getRate() + " is invalid");
        }
        return ratingRepository.save(rating);
    }

    @Override
    public boolean deleteRating(int id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RatingNotFoundException("Rating with id " + id + " not found"));

        ratingRepository.deleteById(id);
        return true;
    }
}
