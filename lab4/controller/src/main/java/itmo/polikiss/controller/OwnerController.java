package itmo.polikiss.controller;

import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.dto.OwnerDto;
import itmo.polikiss.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;
    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/{id}")
    public OwnerDto findOwnerById(@PathVariable long id){
        return ownerService.findOwnerById(id);
    }
    @PostMapping
    public ResponseEntity<?> CreateOwner(@RequestBody OwnerDto ownerDto){
        ownerService.CreateOwner(ownerDto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable long id){
        ownerService.deleteOwnerDto(ownerService.findOwnerById(id));
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> getKitty(@PathVariable long id, @RequestBody KittyDto kittyDto){
        ownerService.getKitty(id, kittyDto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}/kitty")
    public ResponseEntity<?> looseKitty(@PathVariable long id, @PathVariable(name = "kitty") String kitty){
        ownerService.looseKitty(id, kitty);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public  List<OwnerDto> findAllOwners(){
        return ownerService.findAllOwners();
    }
}
