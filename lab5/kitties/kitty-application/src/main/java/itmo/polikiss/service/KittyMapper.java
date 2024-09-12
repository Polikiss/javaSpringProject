package itmo.polikiss.service;

import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.entity.Kitty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KittyMapper {
    @Autowired
    public static KittyDto mapToKittyDto(Kitty kitty) {
        var dto = new KittyDto(kitty.getId(),
                kitty.getName(),
                kitty.getBday(),
                kitty.getColor(),
                kitty.getBreed(),
                kitty.getOwnerId());
        return dto;
    }

}
