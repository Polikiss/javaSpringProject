package itmo.polikiss.controller;

import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.service.KittyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/kitties")
public class AdminKittyController {
    private final KittyServiceImpl kittyService;

    @Autowired
    public AdminKittyController(KittyServiceImpl kittyService) {

        this.kittyService = kittyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<KittyDto> getKittyById(@PathVariable long id) {
        KittyDto kitty = kittyService.findKittyById(id);
        return new ResponseEntity<>(kitty, HttpStatus.OK);
    }

    @GetMapping
    public List<KittyDto> getKitties(@RequestParam(name = "name", defaultValue = "") String name,
                                     @RequestParam(name = "breed", defaultValue = "") String breed,
                                     @RequestParam(name = "color", defaultValue = "") String color) {
        return kittyService.findKittiesForAdmin(name, breed, color);
    }
}
