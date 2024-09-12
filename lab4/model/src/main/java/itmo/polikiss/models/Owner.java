package itmo.polikiss.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kitty> kitties = new ArrayList<>();
    public Owner(String name, Date bday){
        this.name = name;
        this.bday = bday;
        this.kitties = new ArrayList<>();
    }
    public void addKitty(Kitty kitty) {
        kitties.add(kitty);
    }

    public void removeKitty(Kitty kitty) {
        kitties.remove(kitty);
    }
}
