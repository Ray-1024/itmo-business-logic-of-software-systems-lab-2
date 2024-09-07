package ray1024.blps.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ray1024.blps.model.entity.Role;
import ray1024.blps.model.entity.User;
import ray1024.blps.repository.second.OrderRepository;
import ray1024.blps.repository.first.RoleRepository;
import ray1024.blps.repository.first.UserRepository;

import java.util.Optional;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private RoleRepository roleRepository;


    @PostMapping("{userId}/client")
    public ResponseEntity addClientRole(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty() || user.get().getRoles().stream().anyMatch(role -> role.getName().equals(Role.RoleEnum.ROLE_CLIENT))) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Role> clientRole = roleRepository.findByName(Role.RoleEnum.ROLE_CLIENT);
        user.get().getRoles().add(clientRole.get());
        userRepository.save(user.get());
        return ResponseEntity.ok(user.get());
    }

    @DeleteMapping("/client")
    public ResponseEntity deleteClientRole() {
        return null;
    }

    @PostMapping("/client/order")
    public ResponseEntity order() {
        return null;
    }

    public ResponseEntity addCourierRole() {
        return null;
    }

    public ResponseEntity deleteCourierRole() {
        return null;
    }

    public ResponseEntity findOrderToDelivery() {
        return null;
    }

    public ResponseEntity deliveryOrder() {
        return null;
    }

    public ResponseEntity addPackerRole() {
        return null;
    }

    public ResponseEntity deletePackerRole() {
        return null;
    }

    public ResponseEntity findOrderToPack() {
        return null;
    }

    public ResponseEntity packOrder() {
        return null;
    }
}
