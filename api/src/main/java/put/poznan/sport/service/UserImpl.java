package put.poznan.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.entity.User;
import put.poznan.sport.exception.exceptionClasses.*;
import put.poznan.sport.repository.SportFacilityRepository;
import put.poznan.sport.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email " + user.getEmail() + " already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + user.getId() + " not found"));

        return userRepository.save(user);
    }

    @Override
    public boolean deleteUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        userRepository.delete(user);
        return true;
    }

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

    public void checkIfUserIsManager(Integer id){

        SportFacility sportFacility = sportFacilityRepository.findById(id)
                .orElseThrow(() -> new SportEquipmentNotFoundException("Nie zaleziono obiektu sportowego"));

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
