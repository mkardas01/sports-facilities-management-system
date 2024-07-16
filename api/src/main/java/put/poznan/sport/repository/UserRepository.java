package put.poznan.sport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import put.poznan.sport.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}