package itmo.polikiss.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "owner")
@NoArgsConstructor
@Setter
@Getter
public class Owner {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long Id;
    private String name;
    private Date bday;

    public Owner(String name, Date bday) {
        this.name = name;
        this.bday = bday;
    }
}
