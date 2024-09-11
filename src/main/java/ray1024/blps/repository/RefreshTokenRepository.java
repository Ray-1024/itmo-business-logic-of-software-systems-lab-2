package ray1024.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ray1024.blps.model.entity.RefreshToken;
import ray1024.blps.model.entity.User;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    int deleteByUser(User user);
}
