package itmo.polikiss.dto;


import java.util.Date;

public record KittyCreateDto(String name, Date bday, KittyColor color, String breed, long ownerId) {
}
