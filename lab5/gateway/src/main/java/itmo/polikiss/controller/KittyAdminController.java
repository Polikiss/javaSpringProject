package itmo.polikiss.controller;

import itmo.polikiss.dto.FriendDto;
import itmo.polikiss.dto.KittyCreateDto;
import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.service.GatewayServiceKitty;
import itmo.polikiss.service.GatewayServiceMutableImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("/admin/kitty")
public class KittyAdminController {
    private final GatewayServiceKitty gatewayServiceKitty;
    private final GatewayServiceMutableImpl gatewayServiceMutable;

    @Autowired
    public KittyAdminController(GatewayServiceKitty gatewayServiceKitty, GatewayServiceMutableImpl gatewayServiceMutable) {
        this.gatewayServiceKitty = gatewayServiceKitty;
        this.gatewayServiceMutable = gatewayServiceMutable;
    }

    @GetMapping("/{id}")
    public ResponseEntity<KittyDto> getKittyById(@PathVariable long id) {
        KittyDto kitty = gatewayServiceKitty.findKittyByIdAdmin(id);
        return new ResponseEntity<>(kitty, HttpStatus.OK);
    }

    @GetMapping
    public List<KittyDto> getKitties(@RequestParam(name = "name", defaultValue = "") String name,
                                     @RequestParam(name = "breed", defaultValue = "") String breed,
                                     @RequestParam(name = "color", defaultValue = "") String color) {
        return gatewayServiceKitty.findKittiesForAdmin(name, breed, color);
    }

    @PostMapping
    public ResponseEntity<?> createKitty(@RequestBody KittyCreateDto kittyDto) {
        gatewayServiceMutable.createKitty(kittyDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/friend/{id2}")
    public ResponseEntity<?> looseFriend(@PathVariable long id, @PathVariable long id2)
    {
        var friendDto = new FriendDto(id2, id);
        gatewayServiceMutable.kittyLooseFriend(friendDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/friend/{id2}")
    public ResponseEntity<?> getFriend(@PathVariable long id, @PathVariable long id2) {
        var friendDto = new FriendDto(id2, id);
        gatewayServiceMutable.kittyMakeFriend(friendDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKitty(@PathVariable long id) {
        gatewayServiceMutable.deleteKitty(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
