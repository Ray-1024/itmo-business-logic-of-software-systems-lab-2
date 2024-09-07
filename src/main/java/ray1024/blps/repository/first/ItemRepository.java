package ray1024.blps.repository.first;

import org.springframework.data.jpa.repository.JpaRepository;
import ray1024.blps.model.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
