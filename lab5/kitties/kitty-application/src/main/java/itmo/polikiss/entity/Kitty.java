package itmo.polikiss.entity;

import itmo.polikiss.dto.KittyColor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "kitty")
@NoArgsConstructor
@Setter
@Getter
public class Kitty {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Getter
    private String name;

    private Date bday;
    private KittyColor color;
    private String breed;
    private Long ownerId;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "kittyFriend",
            joinColumns = { @JoinColumn(name = "first_kitty_id") },
            inverseJoinColumns = { @JoinColumn(name = "second_kitty_id")}
    )
    private List<Kitty> kittyFriends = new ArrayList<>();

    public Kitty(Long ownerId, String name, Date bday, String kittyColor, String breed){
        this.name = name;
        this.bday = bday;
        this.color = getKittyColorType(kittyColor);
        this.breed = breed;
        this.ownerId = ownerId;
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
