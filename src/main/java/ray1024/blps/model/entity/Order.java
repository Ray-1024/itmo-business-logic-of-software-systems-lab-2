package ray1024.blps.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User client;

    @ManyToOne(fetch = FetchType.EAGER)
    private User packer;

    @ManyToOne(fetch = FetchType.EAGER)
    private User courier;

    @ManyToOne(fetch = FetchType.EAGER)
    private OrderStatus status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "order_itemstack", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "itemstack_id"))
    private Set<ItemStack> itemStacks = new HashSet<>();
}
