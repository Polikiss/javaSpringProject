package itmo.polikiss.controller;

import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.models.KittyColor;
import itmo.polikiss.services.KittyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/kitties")
public class KittyController {
    private final KittyServiceImpl kittyService;

    @Autowired
    public KittyController(KittyServiceImpl kittyService) {

        this.kittyService = kittyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<KittyDto> getKittyById(@PathVariable long id) {

        if (!isAdmin()) {
            if (!kittyService.isCurrentOwnersKitty(id)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        KittyDto kitty = kittyService.findKittyById(id);
        return new ResponseEntity<>(kitty, HttpStatus.OK);
    }

    @GetMapping
    public List<KittyDto> getKitties(@RequestParam(name = "name", defaultValue = "") String name,
                                     @RequestParam(name = "breed", defaultValue = "") String breed,
                                     @RequestParam(name = "color", defaultValue = "") String color) {


        if (isAdmin()) {
            return kittyService.findKittiesForAdmin(name, breed, color);
        }

        return kittyService.findKittiesForUser(name, breed, color);
    }

    @PostMapping
    public ResponseEntity<?> createKitty(@RequestBody KittyDto kittyDto) {
        kittyService.createKitty(kittyDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/friend/{id2}")
    public ResponseEntity<?> looseFriend(@PathVariable long id, @PathVariable long id2) {
        kittyService.looseFriend(id, id2);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/friend/{id2}")
    public ResponseEntity<?> getFriend(@PathVariable long id, @PathVariable long id2) {
        kittyService.getFriend(id, id2);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKitty(@PathVariable long id) {
        if (!isAdmin()) {
            if (!kittyService.isCurrentOwnersKitty(id)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        kittyService.deleteKitty(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
}
