package ray1024.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ray1024.blps.model.entity.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
