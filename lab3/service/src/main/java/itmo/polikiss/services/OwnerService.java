package itmo.polikiss.services;

import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.dto.OwnerDto;
import itmo.polikiss.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public interface OwnerService {

    OwnerDto findOwnerById(long id);
    OwnerDto findOwnerByName(String name);
    void getKitty(long id, KittyDto kittyDto);
    void CreateOwner(OwnerDto ownerDto);
    void deleteOwner(Owner owner);
    void deleteOwnerDto(OwnerDto owner);
    void updateOwner(Owner owner);
    void getKitty(OwnerDto ownerDto, String kittyName, Date bday, String breed, String color);
    void looseKitty(long id, String kittyName);
    List<OwnerDto> findAllOwners();

}
