package ray1024.blss.lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ray1024.blss.lab2.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
