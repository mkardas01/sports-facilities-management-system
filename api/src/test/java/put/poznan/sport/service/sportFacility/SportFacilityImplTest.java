package put.poznan.sport.service.sportFacility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import put.poznan.sport.dto.SportFacility.SportFacilityDTO;
import put.poznan.sport.entity.Authority;
import put.poznan.sport.entity.User;
import put.poznan.sport.entity.sportFacility.SportFacility;
import put.poznan.sport.entity.sportFacility.SportFacilityType;
import put.poznan.sport.exception.exceptionClasses.SportFacilityNotFoundException;
import put.poznan.sport.repository.SportFacilityRepository;
import put.poznan.sport.service.user.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SportFacilityImplTest {

    @Mock
    private SportFacilityRepository sportFacilityRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private SportFacilityImpl sportFacilityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSportFacility_ShouldCreateNewFacility() {
        // given
        User manager = User.builder()
                .id(1)
                .email("manager@example.com")
                .authorities(Collections.singleton(Authority.MANAGER))
                .build();

        SportFacilityDTO dto = SportFacilityDTO.builder()
                .name("Test Facility")
                .description("Test Description")
                .address("Test Address")
                .type(SportFacilityType.SILOWNIA.name())
                .membershipRequired(true)
                .imageUrl("test-image.jpg")
                .build();

        when(userService.getCurrentUsername()).thenReturn("manager@example.com");
        when(sportFacilityRepository.save(any(SportFacility.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // when
        SportFacility result = sportFacilityService.createSportFacility(dto);

        // then
        assertNotNull(result);
        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getDescription(), result.getDescription());
        assertEquals(dto.getAddress(), result.getAddress());
        assertEquals(SportFacilityType.SILOWNIA.getName(), result.getType());
        assertEquals(dto.isMembershipRequired(), result.isMembershipRequired());
        assertEquals(dto.getImageUrl(), result.getImageUrl());
    }

    @Test
    void getSportFacilityById_ShouldThrowException_WhenFacilityNotFound() {
        // given
        int id = 999;
        when(sportFacilityRepository.findById(id)).thenReturn(Optional.empty());

        // when & then
        assertThrows(SportFacilityNotFoundException.class, 
            () -> sportFacilityService.getSportFacilityById(id));
    }

    @Test
    void updateSportFacility_ShouldUpdateExistingFacility() {
        // given
        int facilityId = 1;
        SportFacility existingFacility = SportFacility.builder()
                .id(facilityId)
                .name("Old Name")
                .description("Old Description")
                .build();

        SportFacilityDTO updateDto = SportFacilityDTO.builder()
                .id(facilityId)
                .name("New Name")
                .description("New Description")
                .type(SportFacilityType.BASEN.name())
                .build();

        when(sportFacilityRepository.findById(facilityId)).thenReturn(Optional.of(existingFacility));
        when(sportFacilityRepository.save(any(SportFacility.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // when
        SportFacility result = sportFacilityService.updateSportFacility(updateDto);

        // then
        assertNotNull(result);
        assertEquals(updateDto.getName(), result.getName());
        assertEquals(updateDto.getDescription(), result.getDescription());
        assertEquals(SportFacilityType.BASEN.getName(), result.getType());
    }
} 