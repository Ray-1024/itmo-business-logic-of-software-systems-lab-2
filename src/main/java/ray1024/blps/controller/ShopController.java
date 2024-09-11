package ray1024.blps.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ray1024.blps.model.entity.Shop;
import ray1024.blps.repository.ShopRepository;

import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/shops")
public class ShopController {
    private ShopRepository shopRepository;

    @GetMapping("/")
    public ResponseEntity getAllPaginated(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return ResponseEntity.ok(shopRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNumber)).getContent());
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Shop shop) {
        try {
            return ResponseEntity.ok(shopRepository.save(shop));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{shopId}")
    public ResponseEntity modify(@PathVariable Long shopId, @RequestBody Shop shop) {
        shop.setId(shopId);
        Optional<Shop> shopO = shopRepository.findById(shopId);
        if (shopO.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(shopRepository.save(shop));
    }

    @GetMapping("/{shopId}")
    public ResponseEntity get(@PathVariable Long shopId) {
        Optional<Shop> shopO = shopRepository.findById(shopId);
        if (shopO.isPresent()) return ResponseEntity.ok(shopO.get());
        else return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{shopId}")
    public ResponseEntity delete(@PathVariable Long shopId) {
        Optional<Shop> shopO = shopRepository.findById(shopId);
        if (shopO.isEmpty()) return ResponseEntity.badRequest().build();
        shopRepository.delete(shopO.get());
        return ResponseEntity.ok("success");
    }
}
