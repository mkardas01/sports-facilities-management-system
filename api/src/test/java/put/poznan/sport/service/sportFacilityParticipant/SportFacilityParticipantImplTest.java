package put.poznan.sport.service.sportFacilityParcticipant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import put.poznan.sport.dto.SportFacilityParticipant.SportFacilityParticipantDTO;
import put.poznan.sport.entity.User;
import put.poznan.sport.entity.sportFacility.SportFacility;
import put.poznan.sport.entity.sportFacility.SportFacilityParticipant;
import put.poznan.sport.exception.exceptionClasses.SportFacilityNotFoundException;
import put.poznan.sport.exception.exceptionClasses.UserIsAlreadyMemberException;
import put.poznan.sport.repository.SportFacilityParticipantRepository;
import put.poznan.sport.repository.SportFacilityRepository;
import put.poznan.sport.repository.UserRepository;
import put.poznan.sport.service.user.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SportFacilityParticipantImplTest {

    @Mock
    private SportFacilityParticipantRepository participantRepository;

    @Mock
    private SportFacilityRepository sportFacilityRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private SportFacilityParticipantImpl participantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllFacilityParticipants_ShouldReturnListOfParticipants() {
        // given
        SportFacilityParticipant participant1 = SportFacilityParticipant.builder()
                .userId(1)
                .sportFacilitiesId(1)
                .isActive(1)
                .build();
        SportFacilityParticipant participant2 = SportFacilityParticipant.builder()
                .userId(2)
                .sportFacilitiesId(1)
                .isActive(1)
                .build();

        when(participantRepository.findAll()).thenReturn(Arrays.asList(participant1, participant2));

        // when
        List<SportFacilityParticipant> result = participantService.getAllFacilityParticipants();

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(participantRepository).findAll();
    }

    @Test
    void getUsersBySportFacility_ShouldReturnListOfUsers() {
        // given
        int facilityId = 1;
        User user1 = User.builder().id(1).name("Jan").build();
        User user2 = User.builder().id(2).name("Anna").build();

        SportFacilityParticipant participant1 = SportFacilityParticipant.builder()
                .userId(1)
                .sportFacilitiesId(facilityId)
                .user(user1)
                .build();
        SportFacilityParticipant participant2 = SportFacilityParticipant.builder()
                .userId(2)
                .sportFacilitiesId(facilityId)
                .user(user2)
                .build();

        when(participantRepository.findAllBySportFacilitiesId(facilityId))
                .thenReturn(Optional.of(Arrays.asList(participant1, participant2)));

        // when
        List<User> result = participantService.getUsersBySportFacility(facilityId);

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Jan", result.get(0).getName());
        assertEquals("Anna", result.get(1).getName());
    }

    @Test
    void createSportFacilityParticipant_ShouldCreateNewParticipant() {
        // given
        SportFacilityParticipantDTO dto = SportFacilityParticipantDTO.builder()
                .userId(1)
                .sportFacilitiesId(1)
                .build();

        User user = User.builder().id(1).build();
        SportFacility facility = SportFacility.builder().id(1).build();

        when(userRepository.findById(dto.getUserId())).thenReturn(Optional.of(user));
        when(sportFacilityRepository.findById(dto.getSportFacilitiesId())).thenReturn(Optional.of(facility));
        when(participantRepository.existsSportFacilityParticipantByUserIdAndSportFacilitiesIdAndIsActive(1, 1, 1))
                .thenReturn(false);

        SportFacilityParticipant savedParticipant = SportFacilityParticipant.builder()
                .userId(dto.getUserId())
                .sportFacilitiesId(dto.getSportFacilitiesId())
                .isActive(1)
                .build();

        when(participantRepository.save(any(SportFacilityParticipant.class))).thenReturn(savedParticipant);

        // when
        SportFacilityParticipant result = participantService.createSportFacilityParticipant(dto);

        // then
        assertNotNull(result);
        assertEquals(dto.getUserId(), result.getUserId());
        assertEquals(dto.getSportFacilitiesId(), result.getSportFacilitiesId());
        assertEquals(1, result.getIsActive());
    }

    @Test
    void createSportFacilityParticipant_ShouldThrowException_WhenUserIsAlreadyMember() {
        // given
        SportFacilityParticipantDTO dto = SportFacilityParticipantDTO.builder()
                .userId(1)
                .sportFacilitiesId(1)
                .build();

        User user = User.builder().id(1).build();
        SportFacility facility = SportFacility.builder().id(1).build();

        when(userRepository.findById(dto.getUserId())).thenReturn(Optional.of(user));
        when(sportFacilityRepository.findById(dto.getSportFacilitiesId())).thenReturn(Optional.of(facility));
        when(participantRepository.existsSportFacilityParticipantByUserIdAndSportFacilitiesIdAndIsActive(1, 1, 1))
                .thenReturn(true);

        // when & then
        assertThrows(UserIsAlreadyMemberException.class, 
            () -> participantService.createSportFacilityParticipant(dto));
    }

    @Test
    void changeParticipantStatus_ShouldToggleActiveStatus() {
        // given
        SportFacilityParticipantDTO dto = SportFacilityParticipantDTO.builder()
                .userId(1)
                .sportFacilitiesId(1)
                .build();

        SportFacility facility = SportFacility.builder().id(1).build();
        SportFacilityParticipant existingParticipant = SportFacilityParticipant.builder()
                .userId(1)
                .sportFacilitiesId(1)
                .isActive(1)
                .build();

        when(sportFacilityRepository.findById(dto.getSportFacilitiesId())).thenReturn(Optional.of(facility));
        when(participantRepository.findById(any())).thenReturn(Optional.of(existingParticipant));
        when(participantRepository.save(any(SportFacilityParticipant.class))).thenReturn(existingParticipant);

        // when
        SportFacilityParticipant result = participantService.changeParticipantStatus(dto);

        // then
        assertNotNull(result);
        assertEquals(0, result.getIsActive()); // powinno zmienić status z 1 na 0
    }
} 