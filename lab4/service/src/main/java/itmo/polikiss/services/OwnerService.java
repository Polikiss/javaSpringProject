package itmo.polikiss.services;

import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.dto.OwnerDto;
import itmo.polikiss.models.Owner;

import java.util.Date;
import java.util.List;

public interface OwnerService {

    OwnerDto findOwnerById(long id);
    OwnerDto findOwnerByName(String name);
    void getKitty(long id, KittyDto kittyDto);
    void CreateOwner(OwnerDto ownerDto);
    void deleteOwnerDto(OwnerDto owner);
    void getKitty(OwnerDto ownerDto, String kittyName, Date bday, String breed, String color);
    void looseKitty(long id, String kittyName);
    List<OwnerDto> findAllOwners();

}
