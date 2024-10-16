package put.poznan.sport.service.sportFacility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.dto.SportFacility.SportFacilityDTO;
import put.poznan.sport.entity.sportFacility.SportFacility;
import put.poznan.sport.entity.User;
import put.poznan.sport.entity.sportFacility.SportFacilityType;
import put.poznan.sport.exception.exceptionClasses.SportFacilityNotFoundException;
import put.poznan.sport.exception.exceptionClasses.UserNotFoundException;
import put.poznan.sport.repository.SportFacilityRepository;
import put.poznan.sport.repository.UserRepository;
import put.poznan.sport.service.user.UserService;

import java.util.Collections;
import java.util.List;

@Service
public class SportFacilityImpl implements SportFacilityService {

    @Autowired
    private SportFacilityRepository sportFacilityRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<SportFacility> getAllFacilities() {
        return sportFacilityRepository.findAll();
    }

    @Override
    public SportFacility getSportFacilityById(int id) {
        return sportFacilityRepository.findById(id)
                .orElseThrow(() -> new SportFacilityNotFoundException("SportFacility with id " + id + " not found"));
    }

    @Override
    public SportFacility createSportFacility(SportFacilityDTO facilityDTO) {
        User currentUser = userRepository.findByEmail(userService.getCurrentUsername()).orElseThrow(() -> new UserNotFoundException("Nie znaleziono użytkownika"));

        SportFacility sportFacility = SportFacility.builder()
                .name(facilityDTO.getName())
                .description(facilityDTO.getDescription())
                .address(facilityDTO.getAddress())
                .type(SportFacilityType.valueOf(facilityDTO.getType()).getName())
                .membershipRequired(facilityDTO.isMembershipRequired())
                .imageUrl(facilityDTO.getImageUrl())
                .managers(Collections.singletonList(currentUser))
                .build();

        return sportFacilityRepository.save(sportFacility);
    }

    @Override
    public SportFacility updateSportFacility(SportFacilityDTO facilityDTO) {
        SportFacility sportFacility = sportFacilityRepository.findById(facilityDTO.getId())
                .orElseThrow(() -> new SportFacilityNotFoundException("Nie znaleziono obiektu o id " + facilityDTO.getId()));

        if (facilityDTO.getName() != null) {
            sportFacility.setName(facilityDTO.getName());
        }
        if (facilityDTO.getDescription() != null) {
            sportFacility.setDescription(facilityDTO.getDescription());
        }
        if (facilityDTO.getAddress() != null) {
            sportFacility.setAddress(facilityDTO.getAddress());
        }
        if (facilityDTO.getType() != null) {
            sportFacility.setType(SportFacilityType.valueOf(facilityDTO.getType()).getName());
        }

        sportFacility.setMembershipRequired(facilityDTO.isMembershipRequired());

        if (facilityDTO.getImageUrl() != null) {
            sportFacility.setImageUrl(facilityDTO.getImageUrl());
        }

        return sportFacilityRepository.save(sportFacility);
    }

    @Override
    public void deleteSportFacility(int id) {
        SportFacility facility = sportFacilityRepository.findById(id)
                .orElseThrow(() -> new SportFacilityNotFoundException("Nie znaleziono obiektu o id " + id));

        sportFacilityRepository.delete(facility);
    }
}
