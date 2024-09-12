package itmo.polikiss.dto;

import itmo.polikiss.models.KittyColor;
import itmo.polikiss.models.Owner;
import lombok.Value;

import java.time.LocalDate;

@Value
public class KittyDto {
    Long kitty_id;
    String name;
    LocalDate bday;
    KittyColor color;
    String breed;
    Owner owner;
}
