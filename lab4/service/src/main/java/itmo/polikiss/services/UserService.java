package itmo.polikiss.services;

import itmo.polikiss.models.User;
import org.springframework.stereotype.Component;


public interface UserService {
    User createUser(String username, String password, String UserRole, long ownerId);
}
