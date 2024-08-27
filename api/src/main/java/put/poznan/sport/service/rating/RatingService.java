package put.poznan.sport.service.rating;


import put.poznan.sport.dto.Rating.CreateRating;
import put.poznan.sport.dto.Rating.Rating;
import put.poznan.sport.entity.User;

import java.util.List;

public interface RatingService {


    public List<Rating> getAllRatings();

    public Rating getRatingById(int id);

    public Rating createRating(Rating rating);

    public Rating updateRating(Rating rating);

    public void deleteRating(User user, String type, int id);

}
