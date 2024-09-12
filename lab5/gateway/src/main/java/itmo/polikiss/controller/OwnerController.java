package itmo.polikiss.controller;
import itmo.polikiss.dto.OwnerCreateDto;
import itmo.polikiss.dto.OwnerDto;
import itmo.polikiss.service.GatewayServiceMutableImpl;
import itmo.polikiss.service.GatewayServiceOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final GatewayServiceMutableImpl gatewayService;
    private final GatewayServiceOwner gatewayServiceOwner;

    @Autowired
    public OwnerController(GatewayServiceMutableImpl gatewayService, GatewayServiceOwner gatewayServiceOwner) {
        this.gatewayService = gatewayService;
        this.gatewayServiceOwner = gatewayServiceOwner;
    }

    @GetMapping("/{id}")
    public OwnerDto findOwnerById(@PathVariable long id){

        return gatewayServiceOwner.findOwnerById(id);
    }
    @PostMapping
    public ResponseEntity<?> CreateOwner(@RequestBody OwnerCreateDto ownerDto){
        gatewayService.createOwner(ownerDto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable long id){

        gatewayService.deleteOwner(id);
    }

    @GetMapping
    public  List<OwnerDto> findAllOwners(){

        return gatewayServiceOwner.findAllOwners();
    }
}
