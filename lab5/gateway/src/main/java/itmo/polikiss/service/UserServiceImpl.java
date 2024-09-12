package itmo.polikiss.service;

import itmo.polikiss.dto.UserDto;
import itmo.polikiss.entity.User;
import itmo.polikiss.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Autowired
    private UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDto createUser(UserDto userDto) {
        User user = new User(
                userDto.username(),
                encoder().encode(userDto.password()),
                userDto.role(),
                userDto.ownerId());
        userRepo.save(user);
        return  UserMaper.mapToUserDto(user);
    }

    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
