package ray1024.blss.lab2.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "client_id")
    private User client;

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "packer_id")
    private User packer;

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "coutrier_id")
    private User courier;

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "status_id")
    private OrderStatus status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "order_item", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items = new HashSet<>();
}
