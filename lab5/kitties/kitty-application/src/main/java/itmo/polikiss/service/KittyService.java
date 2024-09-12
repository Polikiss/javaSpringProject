package itmo.polikiss.service;

import itmo.polikiss.dto.FriendDto;
import itmo.polikiss.dto.KittyCreateDto;
import itmo.polikiss.dto.KittyDto;

import java.util.List;

public interface KittyService {
    KittyDto findKittyById(long id, long ownerId);
    KittyDto findKittyById(long id);
    /*void deleteKitty(long id);
    void makeFriend(FriendDto friendDto);
    void looseFriend(FriendDto friendDto);
    KittyDto createKitty(KittyCreateDto kittyDto);*/
    List<KittyDto> findKittiesForUser(String name, String breed, String color, Long id);
    List<KittyDto> findKittiesForAdmin(String name, String breed, String color);
}
