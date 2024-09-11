package ray1024.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ray1024.blps.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
