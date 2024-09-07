package ray1024.blps.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

    public enum RoleEnum {
        ROLE_CLIENT, ROLE_PACKER, ROLE_COURIER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;
}
