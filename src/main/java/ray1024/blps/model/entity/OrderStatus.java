package ray1024.blps.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "orderstatus")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderStatus {
    public enum Status {
        ORDERED, PACKING, PACKED, DELIVERING, DELIVERED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status name;
}
