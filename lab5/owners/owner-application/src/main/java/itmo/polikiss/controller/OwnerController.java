package itmo.polikiss.controller;

import itmo.polikiss.dto.OwnerDto;
import itmo.polikiss.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public  List<OwnerDto> findAllOwners(){
        return ownerService.findAllOwners();
    }
}
