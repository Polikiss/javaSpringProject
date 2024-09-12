package itmo.polikiss.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "kitty")
@NoArgsConstructor
@Setter
@Getter
public class Kitty {
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long kitty_id;
    @Getter
    private String name;

    private LocalDate bday;
    private KittyColor color;
    private String breed;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    @Getter
    @Setter
    private Owner owner;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "kittyFriend",
            joinColumns = { @JoinColumn(name = "first_kitty_id") },
            inverseJoinColumns = { @JoinColumn(name = "second_kitty_id")}
    )
    private List<Kitty> kittyFriends = new ArrayList<>();

    public Kitty(Owner owner, String name, LocalDate bday, String kittyColor, String breed){
        this.name = name;
        this.bday = bday;
        this.color = getKittyColorType(kittyColor);
        this.breed = breed;
        this.owner = owner;
        this.kittyFriends = new ArrayList<>();

    }
    public void addKittyFriend(Kitty kittyFriend) {
       kittyFriends.add(kittyFriend);
    }

    public void removeKittyFriend(Kitty kittyFriend) {
        kittyFriends.remove(kittyFriend);
    }
    private KittyColor getKittyColorType(String color){
        return switch (color.toLowerCase()) {
            case "grey" -> KittyColor.GREY;
            case "black" -> KittyColor.BLACK;
            case "ginger" -> KittyColor.GINGER;
            case "multicolor" -> KittyColor.MULTICOLOR;
            default -> KittyColor.NULL;
        };
    }

}
