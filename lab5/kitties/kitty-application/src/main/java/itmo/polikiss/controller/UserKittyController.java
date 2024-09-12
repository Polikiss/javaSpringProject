package itmo.polikiss.controller;

import itmo.polikiss.dto.KittyDto;

import itmo.polikiss.service.KittyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitties")
public class UserKittyController {
    private final KittyServiceImpl kittyService;

    @Autowired
    public UserKittyController(KittyServiceImpl kittyService) {
        this.kittyService = kittyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<KittyDto> getKittyById(@PathVariable long id,
                                                 @RequestParam(name = "ownerId") Long ownerId)
    {
        KittyDto kitty = kittyService.findKittyById(id, ownerId);
        return new ResponseEntity<>(kitty, HttpStatus.OK);
    }

    @GetMapping
    public List<KittyDto> getKittiesUser(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "breed", defaultValue = "") String breed,
            @RequestParam(name = "color", defaultValue = "") String color,
            @RequestParam(name = "ownerId") Long ownerId) {
        return kittyService.findKittiesForUser(name, breed, color, ownerId);
    }

}
