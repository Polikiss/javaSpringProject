package itmo.polikiss.service;

import itmo.polikiss.dto.OwnerDto;
import itmo.polikiss.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {
    @Autowired
    public static OwnerDto mapToOwnerDto(Owner owner) {
        var dto = new OwnerDto(owner.getId(),
                owner.getName(),
                owner.getBday());
        return dto;
    }
}
