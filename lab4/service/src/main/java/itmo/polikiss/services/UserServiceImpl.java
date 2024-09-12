package itmo.polikiss.services;

import itmo.polikiss.dto.MappingUtils;
import itmo.polikiss.dto.UserDto;
import itmo.polikiss.models.Role;
import itmo.polikiss.models.User;
import itmo.polikiss.repo.OwnerRepo;
import itmo.polikiss.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    private final UserRepo userRepo;
    private final OwnerRepo ownerRepo;

    @Autowired
    private UserServiceImpl(UserRepo userRepo, OwnerRepo ownerRepos) {
        this.userRepo = userRepo;
        this.ownerRepo = ownerRepos;
    }

    public UserDto createUser(String username, String password, Role role, long ownerId) {
        if (ownerRepo.findById(ownerId).isEmpty()) {
            throw new RuntimeException("Owner not found");
        }
        if (userRepo.findUserByOwner(ownerRepo.findById(ownerId)).isPresent()) {
            throw new  RuntimeException("Owner already exists as user");
        }
        User user = new User(username, encoder().encode(password), role, ownerRepo.findById(ownerId).orElseThrow());
        userRepo.save(user);
        return MappingUtils.mapToUserDto(user);
    }

    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
