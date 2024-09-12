package itmo.polikiss.services;
import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.dto.MappingUtils;
import itmo.polikiss.models.Kitty;
import itmo.polikiss.models.KittyColor;
import itmo.polikiss.models.Owner;
import itmo.polikiss.repo.KittyRepo;
import itmo.polikiss.repo.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Transactional(readOnly = true)
@Service
public class KittyServiceImpl implements KittyService {

    private final KittyRepo kittyRepo;
    private final OwnerRepo ownerRepo;

    @Autowired
    public KittyServiceImpl(KittyRepo kittyRepo, OwnerRepo ownerRepo) {
        this.kittyRepo = kittyRepo;
        this.ownerRepo = ownerRepo;
    }

    public KittyDto findKittyById(long id) {
        var kitty = kittyRepo.findById(id).orElse(null);
        if (kitty != null) {
            return MappingUtils.mapToKittyDto(kitty);
        }
        throw new RuntimeException("Kitty is not found");
    }


    public List<KittyDto> findKittyByName(String name){
        var list = kittyRepo.findKittiesByName(name);
        List<KittyDto> kittiesDto = new ArrayList<>();
        for (Kitty kitty : list) {
            kittiesDto.add(MappingUtils.mapToKittyDto(kitty));
        }
        return kittiesDto;


    }
    @Transactional
    public void saveKitty(Kitty kitty) {
        kittyRepo.save(kitty);
    }
    @Transactional
    public void deleteKitty(long id) {
        var kitty = kittyRepo.findById(id).orElse(null);
        kittyRepo.delete(kitty);
    }
    @Transactional
    public void deleteKitty(Kitty kitty) {
        kittyRepo.delete(kitty);
    }
    @Transactional
    public void getFriend(long firstKittyId, long kittyFriendId){
        var firstKitty = kittyRepo.findById(firstKittyId).orElse(null);
        var kittyFriend = kittyRepo.findById(kittyFriendId).orElse(null);
        firstKitty.addKittyFriend(kittyFriend);
        kittyFriend.addKittyFriend(firstKitty);
        kittyRepo.save(firstKitty);
        kittyRepo.save(kittyFriend);
    }
    @Transactional
    public void looseFriend(long kittyId,  long kittyFriendId){
        var kitty = kittyRepo.findById(kittyId).orElse(null);
        var kittyFriend = kittyRepo.findById(kittyFriendId).orElse(null);
        kitty.removeKittyFriend(kittyFriend);
        kittyFriend.removeKittyFriend(kitty);
        kittyRepo.save(kitty);
        kittyRepo.save(kittyFriend);
    }
    @Transactional
    public KittyDto createKitty(Owner owner, String name, Date bday, String breed, String color){
        Kitty kitty = new Kitty(owner, name, bday, color, breed);
        kittyRepo.save(kitty);
        return MappingUtils.mapToKittyDto(kitty);
    }
    @Transactional
    public KittyDto createKitty(KittyDto kittyDto){
        long ownerId = kittyDto.getOwnerId();
        var owner = ownerRepo.findById(ownerId).orElse(null);
        if (owner == null) {
            throw new RuntimeException("Owner is not found");
        }
        Kitty kitty = new Kitty(
                owner,
                kittyDto.getName(),
                kittyDto.getBday(),
                kittyDto.getColor().toString(),
                kittyDto.getBreed()
        );
        kittyRepo.save(kitty);
        return MappingUtils.mapToKittyDto(kitty);
    }

    @Transactional
    public void updateKitty(Kitty kitty) {

        kittyRepo.save(kitty);
    }

    public List<KittyDto> findAllKitties() {
        var kitties = kittyRepo.findAll();
        List<KittyDto> kittiesDto = new ArrayList<>();
        for (Kitty kitty : kitties) {
            kittiesDto.add(MappingUtils.mapToKittyDto(kitty));
        }
        return kittiesDto;
    }

    public List<KittyDto> findKittiesByName(String name) {
        var kitties = kittyRepo.findKittiesByName(name);
        List<KittyDto> kittiesDto = new ArrayList<>();
        for (Kitty kitty : kitties) {
            kittiesDto.add(MappingUtils.mapToKittyDto(kitty));
        }
        return kittiesDto;
    }

    public List<KittyDto> findKittiesByBreed(String breed) {
        var kitties = kittyRepo.findKittiesByBreed(breed);
        List<KittyDto> kittiesDto = new ArrayList<>();
        for (Kitty kitty : kitties) {
            kittiesDto.add(MappingUtils.mapToKittyDto(kitty));
        }
        return kittiesDto;
    }

    public List<KittyDto> findKittiesByColor(KittyColor color) {
        var kitties = kittyRepo.findKittiesByColor(color);
        List<KittyDto> kittiesDto = new ArrayList<>();
        for (Kitty kitty : kitties) {
            kittiesDto.add(MappingUtils.mapToKittyDto(kitty));
        }
        return kittiesDto;
    }


    public List<KittyDto> findKittiesByNameAndBreedAndColor(String name, String breed, KittyColor color){
        var kitties = kittyRepo.findKittiesByNameAndBreedAndColor(name, breed, color);
        List<KittyDto> kittiesDto = new ArrayList<>();
        for (Kitty kitty : kitties) {
            kittiesDto.add(MappingUtils.mapToKittyDto(kitty));
        }
        return kittiesDto;
    }

    public List<KittyDto> findKittiesByNameAndBreed(String name, String breed) {
        var kitties = kittyRepo.findKittiesByNameAndBreed(name, breed);
        List<KittyDto> kittiesDto = new ArrayList<>();
        for (Kitty kitty : kitties) {
            kittiesDto.add(MappingUtils.mapToKittyDto(kitty));
        }
        return kittiesDto;
    }

    public List<KittyDto> findKittiesByNameAndColor(String name, KittyColor color) {
        var kitties = kittyRepo.findKittiesByNameAndColor(name, color);
        List<KittyDto> kittiesDto = new ArrayList<>();
        for (Kitty kitty : kitties) {
            kittiesDto.add(MappingUtils.mapToKittyDto(kitty));
        }
        return kittiesDto;
    }

    public List<KittyDto> findKittiesByColorAndBreed(KittyColor color, String breed) {
        var kitties = kittyRepo.findKittiesByColorAndBreed(color, breed);
        List<KittyDto> kittiesDto = new ArrayList<>();
        for (Kitty kitty : kitties) {
            kittiesDto.add(MappingUtils.mapToKittyDto(kitty));
        }
        return kittiesDto;
    }



}
