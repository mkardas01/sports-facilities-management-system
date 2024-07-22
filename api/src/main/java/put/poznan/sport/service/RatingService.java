package put.poznan.sport.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.Rating;
import put.poznan.sport.repository.RatingRepository;

import java.util.List;
import java.util.Optional;

public interface RatingService {


    public List<Rating> getAllRatings();

    public Rating getRatingById(int id);

    public Rating createRating(Rating rating);

    public Rating updateRating(Rating rating);

    public boolean deleteRating(int id);

}
