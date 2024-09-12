package itmo.polikiss.dto;

import itmo.polikiss.models.KittyColor;
import lombok.Value;

import java.util.Date;

@Value
public class KittyDto {
    Long kittyId;
    String name;
    Date bday;
    KittyColor color;
    String breed;
    long ownerId;
}
