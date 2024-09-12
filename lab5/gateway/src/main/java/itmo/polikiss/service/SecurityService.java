package itmo.polikiss.service;

import itmo.polikiss.repo.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private final UserRepo userRepo;

    public SecurityService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public long getOwnerId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepo.findByUsername(username).getOwnerId();
    }
}
