package itmo.polikiss.kitty.console;

import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.models.Kitty;
import itmo.polikiss.services.KittyService;
import picocli.CommandLine;

import java.time.LocalDate;

@CommandLine.Command(name = "kitty")
public class KittyConsole {
    private final KittyService kittyService;
    private final KittyDto kitty;

    public KittyConsole(KittyService kittyService, KittyDto kitty) {
        this.kittyService = kittyService;
        this.kitty = kitty;
    }

    @CommandLine.Command(name = "getFriend", description = "get a new kitty friend")
    public void getFriend(@CommandLine.Parameters(index = "0", description = "Kitty's name") String name) {
        KittyDto friend = kittyService.findKittyByName(name);
        kittyService.getFriend(kitty, friend);
        System.out.printf("Success\n");
    }

    @CommandLine.Command(name = "looseFriend", description = "kitty loose their friend")
    public void looseFriend(@CommandLine.Parameters(index = "0", description = "Kitty's name") String name) {
        KittyDto friend = kittyService.findKittyByName(name);
        kittyService.looseFriend(kitty, friend);
        System.out.printf("Success\n");
    }
}
