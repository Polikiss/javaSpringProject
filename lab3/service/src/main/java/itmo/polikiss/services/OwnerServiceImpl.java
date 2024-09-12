package itmo.polikiss.services;

import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.dto.MappingUtils;
import itmo.polikiss.dto.OwnerDto;
import itmo.polikiss.models.Kitty;
import itmo.polikiss.models.Owner;
import itmo.polikiss.repo.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional(readOnly = true)
@Component
@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepo ownerRepo;
    private final KittyServiceImpl kittyService;
    @Autowired
    public OwnerServiceImpl(OwnerRepo ownerRepo, KittyServiceImpl kittyService) {
        this.ownerRepo = ownerRepo;
        this.kittyService = kittyService;
    }

    public OwnerDto findOwnerById(long id) {
        var owner = ownerRepo.findById(id).orElse(null);
        if (owner != null) {
            return MappingUtils.mapToOwnerDto(owner);
        }
        throw new RuntimeException("owner not found");
    }
    public OwnerDto findOwnerByName(String name){
        var list = findAllOwners();
        for (var owner : list) {
            if (owner.getName().equals(name)) {
                return owner;
            }
        }
        throw new RuntimeException("owner not found");
    }
    @Transactional
    public void CreateOwner(OwnerDto ownerDto) {
        var owner = MappingUtils.mapToOwner(ownerDto);
        ownerRepo.save(owner);
    }
    @Transactional
    public void deleteOwner(Owner owner) {
        ownerRepo.delete(owner);
    }
    @Transactional
    public void deleteOwnerDto(OwnerDto owner) {
        ownerRepo.delete(MappingUtils.mapToOwner(owner));
    }
    @Transactional
    public void updateOwner(Owner owner) {
        ownerRepo.save(owner);
    }
    @Transactional
    public void getKitty(OwnerDto ownerDto, String kittyName, Date bday, String breed, String color) {
        var owner = MappingUtils.mapToOwner(ownerDto);
        Kitty kitty = new Kitty(owner, kittyName, bday, breed, color);
        kittyService.saveKitty(kitty);
        owner.addKitty(kitty);
        ownerRepo.save(owner);
    }
    @Transactional
    public void getKitty(long id, KittyDto kittyDto) {
        var owner = ownerRepo.findById(id).orElse(null);
        Kitty kitty = new Kitty(owner, kittyDto.getName(), kittyDto.getBday(), kittyDto.getBreed(), kittyDto.getColor().toString());
        kittyService.saveKitty(kitty);
        owner.addKitty(kitty);
        ownerRepo.save(owner);
    }
    @Transactional
    public void looseKitty(long id, String kittyName){
        var owner = ownerRepo.findById(id).orElse(null);
        var kitty = kittyService.findKittyByName(kittyName).get(0);
        kittyService.deleteKitty(kitty.getKittyId());
        ownerRepo.save(owner);
    }

    public List<OwnerDto> findAllOwners() {
        var owners = ownerRepo.findAll();
        List<OwnerDto> ownersDto = new ArrayList<>();
        for (Owner owner : owners) {
            ownersDto.add(MappingUtils.mapToOwnerDto(owner));
        }
        return ownersDto;
    }


}
