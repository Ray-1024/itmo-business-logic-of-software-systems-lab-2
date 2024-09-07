package ray1024.blps.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ray1024.blps.model.entity.User;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user) {
        return null;
    }


    public ResponseEntity login() {
        return null;
    }

    public ResponseEntity logout() {
        return null;
    }

    public ResponseEntity takeout() {
        return null;
    }
}
