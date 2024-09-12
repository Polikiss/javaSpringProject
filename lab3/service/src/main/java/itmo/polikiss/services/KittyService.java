package itmo.polikiss.services;

import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.models.Kitty;
import itmo.polikiss.models.KittyColor;
import itmo.polikiss.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public interface KittyService {
    KittyDto findKittyById(long id);
    List<KittyDto> findKittyByName(String name);

    void saveKitty(Kitty kitty);
    void deleteKitty(long id);
    void deleteKitty(Kitty kitty);
    void getFriend(long firstKittyId, long kittyFriendId);
    public List<KittyDto> findKittiesByColor(KittyColor color);
    void looseFriend(long kittyId, long kittyFriendId);
    KittyDto createKitty(Owner owner, String name, Date bday, String breed, String color);
    KittyDto createKitty(KittyDto kittyDto);
    void updateKitty(Kitty kitty);
    List<KittyDto> findKittiesByNameAndBreedAndColor(String name, String breed, KittyColor color);
    List<KittyDto> findAllKitties();
    public List<KittyDto> findKittiesByBreed(String breed);

}
