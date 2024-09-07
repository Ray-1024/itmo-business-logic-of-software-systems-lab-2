package ray1024.blps.repository.second;

import org.springframework.data.jpa.repository.JpaRepository;
import ray1024.blps.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
