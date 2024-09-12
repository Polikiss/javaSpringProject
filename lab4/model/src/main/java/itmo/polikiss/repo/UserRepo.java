package itmo.polikiss.repo;

import itmo.polikiss.models.Owner;
import itmo.polikiss.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findUserByOwner(Optional<Owner> owner);
}
