package itmo.polikiss.dto;

import lombok.Value;

import java.util.Date;

@Value
public class OwnerDto {
    Long owner_id;
    String name;
    Date bday;
}
