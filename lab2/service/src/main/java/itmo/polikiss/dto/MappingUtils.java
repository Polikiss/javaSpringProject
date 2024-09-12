package itmo.polikiss.dto;

import itmo.polikiss.models.Kitty;
import itmo.polikiss.models.Owner;

import java.util.Optional;

public class MappingUtils {
    public static KittyDto mapToKittyDto(Kitty kitty) {
        var dto = new KittyDto(kitty.getKitty_id(),
                kitty.getName(),
                kitty.getBday(),
                kitty.getColor(),
                kitty.getBreed(),
                kitty.getOwner());
        return dto;
    }
    public static Kitty mapToKitty(KittyDto kittyDto) {
        var kitty = new Kitty();
        kitty.setKitty_id(kittyDto.getKitty_id());
        kitty.setName(kittyDto.getName());

        kitty.setBday(kittyDto.getBday());
        kitty.setColor(kittyDto.getColor());
        kitty.setBreed(kittyDto.getBreed());
        kitty.setOwner(kittyDto.getOwner());
        return kitty;
    }
    public static OwnerDto mapToOwnerDto(Owner owner) {
        var dto = new OwnerDto(owner.getOwner_id(),
                owner.getName(),
                owner.getBday());
        return dto;
    }
    public static Owner mapToOwner(OwnerDto ownerDto) {
        var owner = new Owner();
        owner.setOwner_id(ownerDto.getOwner_id());
        owner.setName(ownerDto.getName());
        owner.setBday(ownerDto.getBday());
        return owner;
    }
}