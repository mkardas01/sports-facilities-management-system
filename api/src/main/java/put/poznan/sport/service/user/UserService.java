package put.poznan.sport.service.user;

import put.poznan.sport.dto.User.UserDTO;
import put.poznan.sport.entity.SportFacility;
import put.poznan.sport.entity.User;

import java.util.List;


public interface UserService {


    public List<User> getAllUsers();

    public User getUser();

    public User createUser(User user);

    public User updateUser(UserDTO user);

    public boolean deleteUser();
    public String getCurrentUsername();

    public void checkIfUserIsManagerOrAdmin(SportFacility sportFacility);

}
