package itmo.polikiss.dto;

import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;
 @Value
public class OwnerDto {
    Long owner_id;
    String name;
    LocalDate bday;
}
