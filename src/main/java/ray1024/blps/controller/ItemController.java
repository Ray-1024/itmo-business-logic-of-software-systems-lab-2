package ray1024.blps.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ray1024.blps.model.entity.Item;
import ray1024.blps.repository.ItemRepository;

import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/items")
public class ItemController {

    private ItemRepository itemRepository;

    @GetMapping("/")
    public ResponseEntity getAllPaginated(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return ResponseEntity.ok(itemRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNumber)).getContent());
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Item item) {
        try {
            return ResponseEntity.ok(itemRepository.save(item));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{itemId}")
    public ResponseEntity get(@PathVariable Long itemId) {
        Optional<Item> itemO = itemRepository.findById(itemId);
        if (itemO.isEmpty()) return ResponseEntity.badRequest().build();
        else return ResponseEntity.ok(itemO.get());
    }

    @PutMapping("/{itemId}")
    public ResponseEntity modify(@PathVariable Long itemId, @RequestBody Item item) {
        item.setId(itemId);
        Optional<Item> itemO = itemRepository.findById(itemId);
        if (itemO.isEmpty()) return ResponseEntity.badRequest().build();
        else return ResponseEntity.ok(itemRepository.save(item));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity delete(@PathVariable Long itemId) {
        Optional<Item> itemO = itemRepository.findById(itemId);
        if (itemO.isEmpty()) return ResponseEntity.badRequest().build();
        itemRepository.delete(itemO.get());
        return ResponseEntity.ok("success");
    }
}
