package itmo.polikiss.services;
import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.dto.MappingUtils;
import itmo.polikiss.models.Kitty;
import itmo.polikiss.models.KittyColor;
import itmo.polikiss.models.Owner;
import itmo.polikiss.repo.KittyRepo;
import itmo.polikiss.repo.OwnerRepo;
import itmo.polikiss.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Transactional(readOnly = true)
@Service
public class KittyServiceImpl implements KittyService {

    private final KittyRepo kittyRepo;
    private final OwnerRepo ownerRepo;
    private final UserRepo userRepo;

    @Autowired
    public KittyServiceImpl(KittyRepo kittyRepo, OwnerRepo ownerRepo, UserRepo userRepo) {
        this.kittyRepo = kittyRepo;
        this.ownerRepo = ownerRepo;
        this.userRepo = userRepo;
    }

    public KittyDto findKittyById(long id) {
        var kitty = kittyRepo.findById(id).orElseThrow(() -> new RuntimeException("Kitty is not found"));
        return MappingUtils.mapToKittyDto(kitty);
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

    public List<KittyDto> findKittiesByNameAndOwnerId(String name, long id) {
        var kitties = kittyRepo.findKittiesByNameAndOwnerId(name, id);
        List<KittyDto> kittiesDto = new ArrayList<>();
        for (Kitty kitty : kitties) {
            kittiesDto.add(MappingUtils.mapToKittyDto(kitty));
        }
        return kittiesDto;
    }

    public List<KittyDto> findKittiesByBreedAndOwnerId(String breed, long id) {
        var kitties = kittyRepo.findKittiesByBreedAndOwnerId(breed, id);
        List<KittyDto> kittiesDto = new ArrayList<>();
        for (Kitty kitty : kitties) {
            kittiesDto.add(MappingUtils.mapToKittyDto(kitty));
        }
        return kittiesDto;
    }

    public List<KittyDto> findKittiesByColorAndOwnerId(KittyColor color, long id) {
        var kitties = kittyRepo.findKittiesByColorAndOwnerId(color, id);
        List<KittyDto> kittiesDto = new ArrayList<>();
        for (Kitty kitty : kitties) {
            kittiesDto.add(MappingUtils.mapToKittyDto(kitty));
        }
        return kittiesDto;
    }


    public List<KittyDto> findKittiesByNameAndBreedAndColorAndOwnerId(String name, String breed, KittyColor color, long id){
        var kitties = kittyRepo.findKittiesByNameAndBreedAndColorAndOwnerId(name, breed, color, id);
        List<KittyDto> kittiesDto = new ArrayList<>();
        for (Kitty kitty : kitties) {
            kittiesDto.add(MappingUtils.mapToKittyDto(kitty));
        }
        return kittiesDto;
    }

    public List<KittyDto> findKittiesByNameAndBreedAndOwnerId(String name, String breed, long id) {
        var kitties = kittyRepo.findKittiesByNameAndBreedAndOwnerId(name, breed, id);
        List<KittyDto> kittiesDto = new ArrayList<>();
        for (Kitty kitty : kitties) {
            kittiesDto.add(MappingUtils.mapToKittyDto(kitty));
        }
        return kittiesDto;
    }

    public List<KittyDto> findKittiesByNameAndColorAndOwnerId(String name, KittyColor color, long id) {
        var kitties = kittyRepo.findKittiesByNameAndColorAndOwnerId(name, color, id);
        List<KittyDto> kittiesDto = new ArrayList<>();
        for (Kitty kitty : kitties) {
            kittiesDto.add(MappingUtils.mapToKittyDto(kitty));
        }
        return kittiesDto;
    }

    public List<KittyDto> findKittiesByColorAndBreedAndOwnerId(KittyColor color, String breed, long id) {
        var kitties = kittyRepo.findKittiesByColorAndBreedAndOwnerId(color, breed, id);
        List<KittyDto> kittiesDto = new ArrayList<>();
        for (Kitty kitty : kitties) {
            kittiesDto.add(MappingUtils.mapToKittyDto(kitty));
        }
        return kittiesDto;
    }

    public List<KittyDto> findKittiesForAdmin(String name, String breed, String color){
        KittyColor kittyColor = KittyColor.valueOf(color);
        List<KittyDto> list = new ArrayList<>();
        if (!name.isEmpty()) {
            if (!breed.isEmpty()) {
                if (!color.isEmpty()) {
                    list = findKittiesByNameAndBreedAndColor(name, breed, kittyColor);
                } else {
                    list = findKittiesByNameAndBreed(name, breed);
                }
            } else {
                if (!color.isEmpty()) {
                    list = findKittiesByNameAndColor(name, kittyColor);
                } else {
                    list = findKittiesByName(name);
                }
            }
        } else {
            if (!breed.isEmpty()) {
                if (!color.isEmpty()) {
                    list = findKittiesByColorAndBreed(kittyColor, breed);
                } else {
                    list = findKittiesByBreed(breed);
                }
            } else {
                if (!color.isEmpty()) {
                    list = findKittiesByColor(kittyColor);
                } else {
                    list = findAllKitties();
                }
            }
        }
        return list;

    }

    public List<KittyDto> findKittiesForUser(String name, String breed, String color){
        KittyColor kittyColor = KittyColor.valueOf(color);
        long id = getOwnerId();
        List<KittyDto> list = new ArrayList<>();
        if (!name.isEmpty()) {
            if (!breed.isEmpty()) {
                if (!color.isEmpty()) {
                    list = findKittiesByNameAndBreedAndColorAndOwnerId(name, breed, kittyColor, id);
                } else {
                    list = findKittiesByNameAndBreedAndOwnerId(name, breed, id);
                }
            } else {
                if (!color.isEmpty()) {
                    list = findKittiesByNameAndColorAndOwnerId(name, kittyColor, id);
                } else {
                    list = findKittiesByNameAndOwnerId(name, id);
                }
            }
        } else {
            if (!breed.isEmpty()) {
                if (!color.isEmpty()) {
                    list = findKittiesByColorAndBreedAndOwnerId(kittyColor, breed, id);
                } else {
                    list = findKittiesByBreedAndOwnerId(breed, id);
                }
            } else {
                if (!color.isEmpty()) {
                    list = findKittiesByColorAndOwnerId(kittyColor, id);
                } else {
                    list = findAllKitties();
                }
            }
        }
        return list;

    }

    public boolean isCurrentOwnersKitty(long kittyId) {
        return kittyRepo.findById(kittyId).orElseThrow().getOwner().getId() == getOwnerId();
    }

    private long getOwnerId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepo.findByUsername(username).getOwner().getId();
    }




}
