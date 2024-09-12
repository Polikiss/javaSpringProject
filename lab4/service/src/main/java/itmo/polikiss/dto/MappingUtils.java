package itmo.polikiss.dto;

import itmo.polikiss.models.Kitty;
import itmo.polikiss.models.Owner;
import itmo.polikiss.models.User;
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
                kitty.getOwner().getId());
        return dto;
    }

    public static OwnerDto mapToOwnerDto(Owner owner) {
        var dto = new OwnerDto(owner.getId(),
                owner.getName(),
                owner.getBday());
        return dto;
    }
    public static Owner mapToOwner(OwnerDto ownerDto) {
        var owner = new Owner();
        owner.setId(ownerDto.getOwner_id());
        owner.setName(ownerDto.getName());
        owner.setBday(ownerDto.getBday());
        return owner;
    }
    public static UserDto mapToUserDto(User user) {
        var dto = new UserDto(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getOwner().getId());
        return dto;
    }
}