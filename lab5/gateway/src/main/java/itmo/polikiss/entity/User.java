package itmo.polikiss.entity;

import itmo.polikiss.dto.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    private Long ownerId;

    public User(String username, String password, Role role, Long ownerId) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.ownerId = ownerId;
    }
}