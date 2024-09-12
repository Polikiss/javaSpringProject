package itmo.polikiss.controller;

import itmo.polikiss.dto.UserDto;
import itmo.polikiss.models.User;
import itmo.polikiss.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping()
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userServiceImpl.createUser(userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRole(),
                userDto.getOwnerId());
    }
}
