package itmo.polikiss.owner.console;

import itmo.polikiss.dto.OwnerDto;
import itmo.polikiss.models.Kitty;
import itmo.polikiss.models.Owner;
import itmo.polikiss.services.KittyService;
import itmo.polikiss.services.OwnerService;
import picocli.CommandLine;

import java.time.LocalDate;

@CommandLine.Command(name = "owner")
public class OwnerConsole {
    private final OwnerDto owner;
    private final OwnerService ownerService;

    public OwnerConsole(OwnerDto owner, OwnerService ownerService) {
        this.owner = owner;
        this.ownerService = ownerService;
    }

    @CommandLine.Command(name = "getKitty", description = "get a new kitty")
    public void getKitty(@CommandLine.Parameters(index = "0", description = "Kitty's name") String name,
                             @CommandLine.Parameters(index = "1", description = "cat's birthday") LocalDate bday,
                             @CommandLine.Parameters(index = "2", description = "cat's breed") String breed,
                             @CommandLine.Parameters(index = "3", description = "cat's color") String color) {

        ownerService.getKitty(owner, name, bday, breed, color);
        System.out.printf("Success\n");
    }
    @CommandLine.Command(name = "looseKitty", description = "Loose kitty")
    public void looseKitty(@CommandLine.Parameters(index = "0", description = "Kitty's name") String name){

        ownerService.looseKitty(owner, name);
        System.out.printf("Success\n");
    }
    @CommandLine.Command(name = "giveAwayKitty", description = "give kitty to another ")
    public void giveAwayKitty(@CommandLine.Parameters(index = "0", description = "Kitty's name") String kittyName,
                          @CommandLine.Parameters(index = "1", description = "New owner name") String ownerName){
       ownerService.giveAwayKitty(owner, kittyName, ownerName);
        System.out.printf("Success\n");
    }
}
