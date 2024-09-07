package ray1024.blps.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "itemstack")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;

    private Long count;
}
