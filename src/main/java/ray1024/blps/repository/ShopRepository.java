package ray1024.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ray1024.blps.model.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
