package itmo.polikiss.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owner")
@NoArgsConstructor
@Setter
@Getter
public class Owner {
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long owner_id;
    @Getter
    private String name;
    private LocalDate bday;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kitty> kitties = new ArrayList<>();
    public Owner(String name, LocalDate bday){
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
