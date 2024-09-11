package ray1024.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ray1024.blps.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
