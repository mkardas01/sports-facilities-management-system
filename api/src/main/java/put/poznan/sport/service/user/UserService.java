package put.poznan.sport.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.entity.User;
import put.poznan.sport.repository.UserRepository;

import java.util.List;
import java.util.Optional;


public interface UserService {


    public List<User> getAllUsers();

    public User getUserById(int id);

    public User createUser(User user);

    public User updateUser(User user);

    public boolean deleteUserById(int id);
    public String getCurrentUsername();

    public void checkIfUserIsManager(SportFacility sportFacility);
}
