package put.poznan.sport.service.trainingSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import put.poznan.sport.dto.TraningSession.TrainingSessionDTO;
import put.poznan.sport.entity.TrainingSession;
import put.poznan.sport.entity.sportFacility.SportFacility;
import put.poznan.sport.exception.exceptionClasses.TrainingSessionNotFoundException;
import put.poznan.sport.repository.TrainingSessionRepository;
import put.poznan.sport.service.user.UserService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TrainingSessionImplTest {

    @Mock
    private TrainingSessionRepository trainingSessionRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TrainingSessionImpl trainingSessionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllSessions_ShouldReturnListOfSessions() {
        // given
        TrainingSession session1 = TrainingSession.builder()
                .id(1)
                .name("Training 1")
                .build();
        TrainingSession session2 = TrainingSession.builder()
                .id(2)
                .name("Training 2")
                .build();

        when(trainingSessionRepository.findAll()).thenReturn(Arrays.asList(session1, session2));

        // when
        Optional<List<TrainingSession>> result = trainingSessionService.getAllSessions();

        // then
        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());
    }

    @Test
    void getSessionById_ShouldReturnSession_WhenSessionExists() {
        // given
        int sessionId = 1;
        TrainingSession session = TrainingSession.builder()
                .id(sessionId)
                .name("Test Training")
                .build();

        when(trainingSessionRepository.findById(sessionId)).thenReturn(Optional.of(session));

        // when
        TrainingSession result = trainingSessionService.getSessionById(sessionId);

        // then
        assertNotNull(result);
        assertEquals(sessionId, result.getId());
        assertEquals("Test Training", result.getName());
    }

    @Test
    void getSessionById_ShouldThrowException_WhenSessionNotFound() {
        // given
        int sessionId = 999;
        when(trainingSessionRepository.findById(sessionId)).thenReturn(Optional.empty());

        // when & then
        assertThrows(TrainingSessionNotFoundException.class, 
            () -> trainingSessionService.getSessionById(sessionId));
    }

    @Test
    void createSession_ShouldCreateNewSession() {
        // given
        TrainingSessionDTO dto = new TrainingSessionDTO();
        dto.setName("New Training");
        dto.setCapacity(10);
        dto.setDuration(60);
        dto.setTrainingDate(new Date());

        TrainingSession savedSession = TrainingSession.builder()
                .id(1)
                .name(dto.getName())
                .capacity(dto.getCapacity())
                .duration(dto.getDuration())
                .trainingDate(dto.getTrainingDate())
                .freeBooked(dto.getCapacity())
                .build();

        when(trainingSessionRepository.save(any(TrainingSession.class))).thenReturn(savedSession);

        // when
        TrainingSession result = trainingSessionService.createSession(dto);

        // then
        assertNotNull(result);
        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getCapacity(), result.getCapacity());
        assertEquals(dto.getDuration(), result.getDuration());
        assertEquals(dto.getCapacity(), result.getFreeBooked());
    }

    @Test
    void updateSession_ShouldUpdateExistingSession() {
        // given
        int sessionId = 1;
        TrainingSession existingSession = TrainingSession.builder()
                .id(sessionId)
                .name("Old Name")
                .capacity(5)
                .sportFacility(new SportFacility())
                .build();

        TrainingSessionDTO updateDto = new TrainingSessionDTO();
        updateDto.setTrainingSessionId(sessionId);
        updateDto.setName("New Name");
        updateDto.setCapacity(10);

        when(trainingSessionRepository.findById(sessionId)).thenReturn(Optional.of(existingSession));
        when(trainingSessionRepository.save(any(TrainingSession.class))).thenReturn(existingSession);

        // when
        TrainingSession result = trainingSessionService.updateSession(updateDto);

        // then
        assertNotNull(result);
        assertEquals(updateDto.getName(), result.getName());
        assertEquals(updateDto.getCapacity(), result.getCapacity());
    }
} 