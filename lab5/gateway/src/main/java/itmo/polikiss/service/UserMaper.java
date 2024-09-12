package itmo.polikiss.service;

import itmo.polikiss.dto.UserDto;
import itmo.polikiss.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMaper {
    @Autowired
    public static UserDto mapToUserDto(User user) {
        var dto = new UserDto(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getOwnerId());
        return dto;
    }
}
