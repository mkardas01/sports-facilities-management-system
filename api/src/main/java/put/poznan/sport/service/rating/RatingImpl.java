package put.poznan.sport.service.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.dto.Rating.CreateRating;
import put.poznan.sport.dto.Rating.ObjectType;
import put.poznan.sport.dto.Rating.Rating;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.entity.rating.CoachRating;
import put.poznan.sport.exception.exceptionClasses.CoachNotFoundException;
import put.poznan.sport.repository.CoachRepository;
import put.poznan.sport.repository.TrainingSessionRepository;
import put.poznan.sport.repository.rating.CoachRatingRepository;
import put.poznan.sport.repository.rating.SportFacilityRatingRepository;

import java.util.List;

@Service
public class RatingImpl implements RatingService {

    @Autowired
    private CoachRatingRepository coachRatingRepository;

    @Autowired
    private SportFacilityRatingRepository sportFacilityRatingRepository;

    @Autowired
    private TrainingSessionRepository trainingSessionRepository;

    @Autowired
    private CoachRepository coachRepository;


    @Override
    public List<Rating> getAllRatings() {
        return null;
    }

    @Override
    public Rating getRatingById(int id) {
        return null;
    }

    @Override
    public Rating createRating(Rating rating) {

        if (rating.getObjectType().equals(ObjectType.COACH)){
            Coach coach = coachRepository.findById(rating.getObjectId())
                    .orElseThrow(() -> new CoachNotFoundException("Nie znaleziono trenera"));

            CoachRating coachRating = new CoachRating();

            coachRating.setCoach(coach);
            coachRating.setRate(rating.getRate());
            coachRating.setUser(rating.getAddedBy());

            CoachRating newCoachRaiting = coachRatingRepository.save(coachRating);
            rating.setId(newCoachRaiting.getId());

            return rating;
        }

        return null;
    }

    @Override
    public Rating updateRating(CreateRating rating) {
        return null;
    }

    @Override
    public void deleteRating(int id) {

    }
}
