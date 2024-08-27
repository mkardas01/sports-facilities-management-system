package put.poznan.sport.service.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.dto.Rating.ObjectType;
import put.poznan.sport.dto.Rating.Rating;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.entity.User;
import put.poznan.sport.entity.rating.CoachRating;
import put.poznan.sport.entity.rating.SportFacilityRating;
import put.poznan.sport.exception.exceptionClasses.*;
import put.poznan.sport.repository.CoachRepository;
import put.poznan.sport.repository.SportFacilityRepository;
import put.poznan.sport.repository.TrainingSessionRepository;
import put.poznan.sport.repository.rating.CoachRatingRepository;
import put.poznan.sport.repository.rating.SportFacilityRatingRepository;
import put.poznan.sport.service.user.UserService;

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

    @Autowired
    private SportFacilityRepository sportFacilityRepository;

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
        } else if(rating.getObjectType().equals(ObjectType.SPORT_FACILITY)){
            return handleSportFacilityRatingCreate(rating);
        }

        return null;
    }

    @Override
    public Rating updateRating(Rating rating) {
        if (rating.getObjectType().equals(ObjectType.COACH)){
            return handleCoachRatingUpdate(rating);
        } else if(rating.getObjectType().equals(ObjectType.SPORT_FACILITY)){
            return handleSportFacilityRatingUpdate(rating);
        }
        return null;
    }

    @Override
    public void deleteRating(User user, String type, int id) {
        if (type.equals(ObjectType.COACH.name())){
            handleCoachRatingDelete(user, id);
        }else if (type.equals(ObjectType.SPORT_FACILITY.name())){
            handleSportFacilityRatingDelete(user, id);
        }

    }

    private Rating handleCoachRatingCreat(Rating rating){
        Coach coach = coachRepository.findById(rating.getObjectId())
                .orElseThrow(() -> new CoachNotFoundException("Nie znaleziono trenera"));

        coachRatingRepository.findByUserAndCoach(rating.getAddedBy(), coach)
                .ifPresent(existingRating -> {
                    throw new RatingAlreadyExistsException("Opinia została już prędzej wystawiona");
                });

        CoachRating coachRating = CoachRating.builder()
                .coach(coach)
                .rate(rating.getRate())
                .user(rating.getAddedBy())
                .build();

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

    private Rating handleSportFacilityRatingCreate(Rating rating){
        SportFacility sportFacility = sportFacilityRepository.findById(rating.getObjectId())
                .orElseThrow(() -> new SportFacilityNotFoundException("Nie znaleziono obiektu sportowego"));

        sportFacilityRatingRepository.findByUserAndSportFacility(rating.getAddedBy(), sportFacility)
                .ifPresent(existingRating -> {
                    throw new RatingAlreadyExistsException("Opinia została już prędzej wystawiona");
                });

        SportFacilityRating sportFacilityRating = SportFacilityRating.builder()
                .rate(rating.getRate())
                .sportFacility(sportFacility)
                .user(rating.getAddedBy())
                .build();

        SportFacilityRating newSportFacilityRating = sportFacilityRatingRepository.save(sportFacilityRating);
        rating.setId(newSportFacilityRating.getId());

        return rating;
    }

    private Rating handleSportFacilityRatingUpdate(Rating rating){
        SportFacility sportFacility = sportFacilityRepository.findById(rating.getObjectId())
                .orElseThrow(() -> new SportFacilityNotFoundException("Nie znaleziono obiektu sportowego"));

        SportFacilityRating sportFacilityRating = sportFacilityRatingRepository.findByUserAndSportFacility(rating.getAddedBy(), sportFacility).orElseThrow(() -> new RatingNotFoundException("Nie znaleziono opini"));

        sportFacilityRating.setRate(rating.getRate());

        SportFacilityRating newSportFacilityRating = sportFacilityRatingRepository.save(sportFacilityRating);
        rating.setId(newSportFacilityRating.getId());

        return rating;
    }

    private void handleSportFacilityRatingDelete(User user, int id){

        SportFacilityRating sportFacilityRating = sportFacilityRatingRepository.findByUserAndId(user, id).orElseThrow(() -> new RatingNotFoundException("Nie znaleziono opini"));

        sportFacilityRatingRepository.delete(sportFacilityRating);

    }

}
