package ray1024.blps.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 64)
    private String name;

    @NotBlank
    @Size(min = 1, max = 1024)
    private String description;

    private Long cost;
}
