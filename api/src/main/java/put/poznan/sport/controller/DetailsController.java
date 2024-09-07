package put.poznan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import put.poznan.sport.response.CoachAverageRating;
import put.poznan.sport.response.SportFacilityDetailsResponse;
import put.poznan.sport.dto.Coach.CoachCreateResponse;
import put.poznan.sport.entity.Coach;
import put.poznan.sport.entity.SportEquipment;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.entity.openHour.OpenHour;
import put.poznan.sport.exception.exceptionClasses.SportFacilityNotFoundException;
import put.poznan.sport.repository.SportFacilityRepository;
import put.poznan.sport.service.coach.CoachService;
import put.poznan.sport.service.openHour.OpenHourService;
import put.poznan.sport.service.rating.RatingService;
import put.poznan.sport.service.sportEquipment.SportEquipmentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/details")
public class DetailsController {

    @Autowired
    private SportFacilityRepository sportFacilityRepository;

    @Autowired
    private CoachService coachService;

    @Autowired
    private OpenHourService openHourService;

    @Autowired
    private SportEquipmentService sportEquipmentService;

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<SportFacilityDetailsResponse> getSportFacilityDetails(@PathVariable Integer id) {
        // Pobranie obiektu sportowego
        SportFacility sportFacility = sportFacilityRepository.findById(id)
                .orElseThrow(() -> new SportFacilityNotFoundException("Obiekt sportowy nie znaleziony"));

        // Pobranie godzin otwarcia
        OpenHour openHour = openHourService.getOpenHourById(id);

        // Pobranie listy trenerów i obliczenie średnich ocen
        List<Coach> coaches = sportFacility.getCoaches();
        List<CoachAverageRating> coachRatings = coaches.stream()
                .map(coach -> CoachAverageRating.builder()
                        .coachId(coach.getId())
                        .coachName(coach.getName() + " " + coach.getSurname())
                        .averageRating(ratingService.getCoachAverageRating(coach.getId()))
                        .build())
                .collect(Collectors.toList());

        // Pobranie sprzętu sportowego
        List<SportEquipment> equipment = sportEquipmentService.getEquipmentsBySportFacility(id);

        // Obliczenie średniej oceny obiektu
        Double averageRating = ratingService.getSportFacilityAverageRating(id);

        // Zbudowanie odpowiedzi
        SportFacilityDetailsResponse response = SportFacilityDetailsResponse.builder()
                .id(sportFacility.getId())
                .name(sportFacility.getName())
                .description(sportFacility.getDescription())
                .address(sportFacility.getAddress())
                .type(sportFacility.getType())
                .membershipRequired(sportFacility.isMembershipRequired())
                .imageUrl(sportFacility.getImageUrl())
                .openHours(openHour)
                .coaches(coaches.stream().map(c -> CoachCreateResponse.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .surname(c.getSurname())
                        .sportFacilitiesId(c.getSportFacility().getId())
                        .imageUrl(c.getImageUrl())
                        .build()).collect(Collectors.toList()))
                .equipment(equipment)
                .averageRating(averageRating)
                .coachRatings(coachRatings)
                .build();

        return ResponseEntity.ok(response);
    }
}
