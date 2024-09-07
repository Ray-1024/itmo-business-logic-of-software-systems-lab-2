package ray1024.blps.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "shop")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 64)
    @Column(unique = true)
    private String name;

    @NotBlank
    @Size(min = 1, max = 256)
    private String address;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "shop_itemstack", joinColumns = @JoinColumn(name = "shop_id"), inverseJoinColumns = @JoinColumn(name = "itemstack_id"))
    private Set<ItemStack> itemStacks = new HashSet<>();
}
