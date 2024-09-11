package ray1024.blps.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ray1024.blps.model.entity.Role;
import ray1024.blps.model.entity.User;
import ray1024.blps.model.request.AuthRequestDto;
import ray1024.blps.repository.UserRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    UserRepository userRepository;

    @PostMapping("/singup")
    public ResponseEntity create(@RequestBody AuthRequestDto signupRequest) {
        Optional<User> user = userRepository.findByUsername(signupRequest.getUsername());
        if (user.isPresent()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(userRepository.save(new User(0L, signupRequest.getUsername(), signupRequest.getPassword(), new HashSet<Role>())));
    }


    @PutMapping("/signin")
    public ResponseEntity login(@RequestBody AuthRequestDto signinRequest) {
        Optional<User> user = userRepository.findByUsername(signinRequest.getUsername());
        if (user.isEmpty() || !Objects.equals(user.get().getPassword(), signinRequest.getPassword()))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok("success");
    }

    @PutMapping("/logout")
    public ResponseEntity logout() {
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/takeout")
    public ResponseEntity takeout(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isEmpty()) return ResponseEntity.badRequest().build();
        userRepository.delete(userRepository.findByUsername(user.getUsername()).get());
        return ResponseEntity.ok("success");
    }
}

