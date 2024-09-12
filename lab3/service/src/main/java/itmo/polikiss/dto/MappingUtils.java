package itmo.polikiss.dto;

import itmo.polikiss.models.Kitty;
import itmo.polikiss.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MappingUtils {
    @Autowired
    public static KittyDto mapToKittyDto(Kitty kitty) {
        var dto = new KittyDto(kitty.getKitty_id(),
                kitty.getName(),
                kitty.getBday(),
                kitty.getColor(),
                kitty.getBreed(),
                kitty.getOwner().getOwner_id());
        return dto;
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