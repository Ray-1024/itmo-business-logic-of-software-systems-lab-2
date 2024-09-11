package ray1024.blps.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.web.bind.annotation.*;
import ray1024.blps.model.entity.Order;
import ray1024.blps.model.entity.Role;
import ray1024.blps.model.entity.User;
import ray1024.blps.repository.RoleRepository;
import ray1024.blps.repository.UserRepository;
import ray1024.blps.repository.OrderRepository;

import java.util.Optional;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private RoleRepository roleRepository;



    @PostMapping("/{userId}/client")
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

    @DeleteMapping("/{userId}/client")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ResponseEntity deleteClientRole(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty() || user.get().getRoles().stream().noneMatch(role -> role.getName().equals(Role.RoleEnum.ROLE_CLIENT))) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Role> clientRole = roleRepository.findByName(Role.RoleEnum.ROLE_CLIENT);
        user.get().getRoles().remove(clientRole.get());
        userRepository.save(user.get());
        return ResponseEntity.ok(user.get());
    }

    @PostMapping("/{userId}/client/order")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @Transactional
    public ResponseEntity order(@PathVariable Long userId, @RequestBody Order order) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();
        order.setClient(user.get());
        orderRepository.save(order);
        return ResponseEntity.ok("success");
    }

    @PostMapping("{userId}/courier")
    public ResponseEntity addCourierRole(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty() || user.get().getRoles().stream().anyMatch(role -> role.getName().equals(Role.RoleEnum.ROLE_COURIER))) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Role> clientRole = roleRepository.findByName(Role.RoleEnum.ROLE_COURIER);
        user.get().getRoles().add(clientRole.get());
        userRepository.save(user.get());
        return ResponseEntity.ok(user.get());
    }

    @DeleteMapping("/{userId}/courier")
    public ResponseEntity deleteCourierRole(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty() || user.get().getRoles().stream().noneMatch(role -> role.getName().equals(Role.RoleEnum.ROLE_COURIER))) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Role> clientRole = roleRepository.findByName(Role.RoleEnum.ROLE_COURIER);
        user.get().getRoles().remove(clientRole.get());
        userRepository.save(user.get());
        return ResponseEntity.ok(user.get());
    }

    public ResponseEntity findOrderToDelivery() {
        return null;
    }

    public ResponseEntity deliveryOrder() {
        return null;
    }

    @PostMapping("{userId}/packer")
    public ResponseEntity addPackerRole(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty() || user.get().getRoles().stream().anyMatch(role -> role.getName().equals(Role.RoleEnum.ROLE_PACKER))) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Role> clientRole = roleRepository.findByName(Role.RoleEnum.ROLE_PACKER);
        user.get().getRoles().add(clientRole.get());
        userRepository.save(user.get());
        return ResponseEntity.ok(user.get());
    }

    @DeleteMapping("/{userId}/packer")
    public ResponseEntity deletePackerRole(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty() || user.get().getRoles().stream().noneMatch(role -> role.getName().equals(Role.RoleEnum.ROLE_PACKER))) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Role> clientRole = roleRepository.findByName(Role.RoleEnum.ROLE_PACKER);
        user.get().getRoles().remove(clientRole.get());
        userRepository.save(user.get());
        return ResponseEntity.ok(user.get());
    }

    public ResponseEntity findOrderToPack() {
        return null;
    }

    public ResponseEntity packOrder() {
        return null;
    }
}
