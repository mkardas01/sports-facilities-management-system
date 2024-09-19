package put.poznan.sport.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import put.poznan.sport.dto.User.UserDTO;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.entity.User;
import put.poznan.sport.exception.exceptionClasses.*;
import put.poznan.sport.repository.SportFacilityRepository;
import put.poznan.sport.repository.UserRepository;

import java.util.List;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SportFacilityRepository sportFacilityRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UserNotFoundException("User " + authentication.getName() + " not found"));
    }

    @Override
    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email " + user.getEmail() + " already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        String currentUserEmail = getCurrentUsername();
        User currentUser = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new UserNotFoundException("Nie znaleziono użytkownika z adresem " + currentUserEmail));

        if (userDTO.getName() != null) {
            currentUser.setName(userDTO.getName());
        }
        if (userDTO.getSurname() != null) {
            currentUser.setSurname(userDTO.getSurname());
        }
        if (userDTO.getImageUrl() != null) {
            currentUser.setImageUrl(userDTO.getImageUrl());
        }

        return userRepository.save(currentUser);
    }


    @Override
    public boolean deleteUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        userRepository.delete(user);
        return true;
    }

    @Override
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        }
        return null;
    }

    @Override
    public void checkIfUserIsManager(SportFacility sportFacility){

        if (sportFacility == null) {
            throw new SportFacilityNotFoundException("Nie znaleziono podanego obiektu sportowego w bazie danych");
        }

        String currentUser = this.getCurrentUsername();

        List<String> managerUserNames = sportFacility
                .getManagers()
                .stream()
                .map(User::getUsername)
                .toList();

        if(!managerUserNames.contains(currentUser)){
            throw new InvalidUserException("Nie możesz zarządzać tym obiektem z poziomu tego konta");
        }

    }
}
