package put.poznan.sport.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import put.poznan.sport.dto.User.UserDTO;
import put.poznan.sport.entity.Authority;
import put.poznan.sport.entity.User;
import put.poznan.sport.entity.sportFacility.SportFacility;
import put.poznan.sport.exception.exceptionClasses.InvalidUserException;
import put.poznan.sport.exception.exceptionClasses.UserNotFoundException;
import put.poznan.sport.repository.UserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private UserImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
    }

    @Test
    void getUser_ShouldReturnUser_WhenUserExists() {
        // given
        String email = "test@example.com";
        User expectedUser = User.builder()
                .email(email)
                .name("Test")
                .surname("User")
                .build();
        
        when(authentication.getName()).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(expectedUser));

        // when
        User result = userService.getUser();

        // then
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(userRepository).findByEmail(email);
    }

    @Test
    void getUser_ShouldThrowException_WhenUserDoesNotExist() {
        // given
        String email = "nonexistent@example.com";
        when(authentication.getName()).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // when & then
        assertThrows(UserNotFoundException.class, () -> userService.getUser());
    }

    @Test
    void updateUser_ShouldUpdateUserDetails() {
        // given
        String email = "test@example.com";
        User existingUser = User.builder()
                .email(email)
                .name("Old Name")
                .surname("Old Surname")
                .imageUrl("old-image.jpg")
                .build();
        
        UserDTO updateDTO = new UserDTO();
        updateDTO.setName("New Name");
        updateDTO.setSurname("New Surname");
        updateDTO.setImageUrl("new-image.jpg");

        when(authentication.getName()).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        // when
        User updatedUser = userService.updateUser(updateDTO);

        // then
        assertEquals("New Name", updatedUser.getName());
        assertEquals("New Surname", updatedUser.getSurname());
        assertEquals("new-image.jpg", updatedUser.getImageUrl());
    }

    @Test
    void checkIfUserIsManagerOrAdmin_ShouldThrowException_WhenUserIsNotAuthorized() {
        // given
        String email = "user@example.com";
        User user = User.builder()
                .email(email)
                .authorities(Collections.singleton(Authority.USER))
                .build();
        
        SportFacility facility = SportFacility.builder()
                .managers(Collections.emptyList())
                .build();

        when(authentication.getName()).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // when & then
        assertThrows(InvalidUserException.class, () -> userService.checkIfUserIsManagerOrAdmin(facility));
    }
} 