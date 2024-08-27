package put.poznan.sport.service.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.dto.Rating.CreateRating;
import put.poznan.sport.dto.Rating.ObjectType;
import put.poznan.sport.dto.Rating.Rating;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.entity.User;
import put.poznan.sport.entity.rating.CoachRating;
import put.poznan.sport.exception.exceptionClasses.CoachNotFoundException;
import put.poznan.sport.exception.exceptionClasses.InvalidUserException;
import put.poznan.sport.exception.exceptionClasses.RatingAlreadyExistsException;
import put.poznan.sport.exception.exceptionClasses.RatingNotFoundException;
import put.poznan.sport.repository.CoachRepository;
import put.poznan.sport.repository.TrainingSessionRepository;
import put.poznan.sport.repository.rating.CoachRatingRepository;
import put.poznan.sport.repository.rating.SportFacilityRatingRepository;
import put.poznan.sport.service.user.UserService;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.repository.util.ClassUtils.ifPresent;

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

    @Autowired
    private UserService userService;


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
            return handleCoachRatingCreat(rating);
        }

        return null;
    }

    @Override
    public Rating updateRating(Rating rating) {
        if (rating.getObjectType().equals(ObjectType.COACH)){
            return handleCoachRatingUpdate(rating);
        }
        return null;
    }

    @Override
    public void deleteRating(User user, String type, int id) {
        if (type.equals(ObjectType.COACH.name())){
            handleCoachRatingDelete(user, id);
        }

    }

    private Rating handleCoachRatingCreat(Rating rating){
        Coach coach = coachRepository.findById(rating.getObjectId())
                .orElseThrow(() -> new CoachNotFoundException("Nie znaleziono trenera"));

        coachRatingRepository.findByUserAndCoach(rating.getAddedBy(), coach)
                .ifPresent(existingRating -> {
                    throw new RatingAlreadyExistsException("Opinia została już prędzej wystawiona");
                });

        CoachRating coachRating = new CoachRating();

        coachRating.setCoach(coach);
        coachRating.setRate(rating.getRate());
        coachRating.setUser(rating.getAddedBy());

        CoachRating newCoachRating = coachRatingRepository.save(coachRating);
        rating.setId(newCoachRating.getId());

        return rating;
    }

    private Rating handleCoachRatingUpdate(Rating rating){
        Coach coach = coachRepository.findById(rating.getObjectId())
                .orElseThrow(() -> new CoachNotFoundException("Nie znaleziono trenera"));

        CoachRating coachRating = coachRatingRepository.findByUserAndCoach(rating.getAddedBy(), coach).orElseThrow(() -> new RatingNotFoundException("Nie znaleziono opini"));

        coachRating.setRate(rating.getRate());

        CoachRating newCoachRating = coachRatingRepository.save(coachRating);
        rating.setId(newCoachRating.getId());

        return rating;
    }

    private void handleCoachRatingDelete(User user, int id){

        CoachRating coachRating = coachRatingRepository.findByUserAndId(user, id).orElseThrow(() -> new RatingNotFoundException("Nie znaleziono opini"));

        coachRatingRepository.delete(coachRating);

    }

}
