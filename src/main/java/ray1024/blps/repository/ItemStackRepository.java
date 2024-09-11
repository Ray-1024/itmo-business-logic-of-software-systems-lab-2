package ray1024.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ray1024.blps.model.entity.ItemStack;

public interface ItemStackRepository extends JpaRepository<ItemStack, Long> {
}
