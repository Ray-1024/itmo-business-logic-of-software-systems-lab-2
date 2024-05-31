package ray1024.blss.lab2.model.entity;

import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;

    @NotBlank
    @Column(name = "cost")
    private Long cost;

    @NotBlank
    @Column(name = "count")
    private Long count;

    @NotBlank
    @Column(name = "weight")
    private Long weight;
}
