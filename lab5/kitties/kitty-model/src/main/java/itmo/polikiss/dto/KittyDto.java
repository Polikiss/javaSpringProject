package itmo.polikiss.dto;

import java.util.Date;

public record KittyDto(Long id, String name, Date bday, KittyColor color, String breed, long ownerId) {
}
