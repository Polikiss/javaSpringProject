package itmo.polikiss.dto;

import itmo.polikiss.models.Role;
import lombok.Value;

import java.util.Date;

@Value
public class UserDto {
    int id;
    String username;
    String password;
    Role role;
    long ownerId;
}
