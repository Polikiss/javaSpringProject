package itmo.polikiss.login;

import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.dto.OwnerDto;
import itmo.polikiss.kitty.console.KittyConsole;
import itmo.polikiss.models.Kitty;
import itmo.polikiss.models.Owner;
import itmo.polikiss.owner.console.OwnerConsole;
import itmo.polikiss.services.KittyService;
import itmo.polikiss.services.OwnerService;
import picocli.CommandLine;

import java.time.LocalDate;
import java.util.Scanner;

@CommandLine.Command(name = "login")
public class LoginConsole {
    private final OwnerService ownerService;
    private final KittyService kittyService;

    public LoginConsole(OwnerService ownerService, KittyService kittyService) {
        this.ownerService = ownerService;
        this.kittyService = kittyService;
    }

    @CommandLine.Command(name = "loginKitty", description = "login as kitty")
    public void loginKitty(@CommandLine.Parameters(index = "0", description = "Kitty's name") String name){

        KittyDto kitty = kittyService.findKittyByName(name);
        KittyConsole kittyConsole = new KittyConsole(kittyService, kitty);
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. getFriend\n2. looseFriend\n3. exit");
        String input = scanner.nextLine();
        while (!input.equals("exit")) {
            int exitCode = new CommandLine(kittyConsole).execute(input.split(" "));
            System.out.println("1. getFriend\n2. looseFriend\n3. exit");
            input = scanner.nextLine();
        }
    }
    @CommandLine.Command(name = "loginOwner", description = "login as owner")
    public void loginOwner(@CommandLine.Parameters(index = "0", description = "Owner's name") String name){

        OwnerDto owner = ownerService.findOwnerByName(name);
        OwnerConsole ownerConsole = new OwnerConsole(owner, ownerService);
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. getKitty\n2. looseKitty\n3. giveAwayKitty\n4. exit");
        String input = scanner.nextLine();
        while (!input.equals("exit")) {
            int exitCode = new CommandLine(ownerConsole).execute(input.split(" "));
            System.out.println("1. getKitty\n2. looseKitty\n3. giveAwayKitty\n4. exit");
            input = scanner.nextLine();
        }
    }

    @CommandLine.Command(name = "createOwner", description = "create new owner")
    public void createOwner(@CommandLine.Parameters(index = "0", description = "Owner's name") String name,
                            @CommandLine.Parameters(index = "1", description = "Owner's birthday")LocalDate bday){

        Owner owner = new Owner(name, bday);
        ownerService.saveOwner(owner);
        System.out.println("Success\n");
    }
}
