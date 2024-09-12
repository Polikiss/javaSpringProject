package itmo.polikiss.controller;

import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.models.KittyColor;
import itmo.polikiss.services.KittyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public KittyDto getKittyById(@PathVariable long id) {
        var kitty = kittyService.findKittyById(id);
        return kitty;
    }

    @GetMapping
    public List<KittyDto> getKitties(@RequestParam(name = "name", defaultValue = "") String name,
                                     @RequestParam(name = "breed", defaultValue = "") String breed,
                                     @RequestParam(name = "color", defaultValue = "") String color) {

        KittyColor kittyColor = KittyColor.valueOf(color);
        List<KittyDto> list = new ArrayList<>();
        if (!name.isEmpty()) {
            if (!breed.isEmpty()) {
                if (!color.isEmpty()) {
                    list = kittyService.findKittiesByNameAndBreedAndColor(name, breed, kittyColor);
                } else {
                    list = kittyService.findKittiesByNameAndBreed(name, breed);
                }
            } else {
                if (!color.isEmpty()) {
                    list = kittyService.findKittiesByNameAndColor(name, kittyColor);
                } else {
                    list = kittyService.findKittiesByName(name);
                }
            }
        } else {
            if (!breed.isEmpty()) {
                if (!color.isEmpty()) {
                    list = kittyService.findKittiesByColorAndBreed(kittyColor, breed);
                } else {
                    list = kittyService.findKittiesByBreed(breed);
                }
            } else {
                if (!color.isEmpty()) {
                    list = kittyService.findKittiesByColor(kittyColor);
                } else {
                    list = kittyService.findAllKitties();
                }
            }
        }
        return list;
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
    public void deleteKitty(@PathVariable long id) {
        kittyService.deleteKitty(id);
    }
}
