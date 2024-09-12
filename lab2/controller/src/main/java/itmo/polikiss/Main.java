package itmo.polikiss;

import itmo.polikiss.dao.KittyDao;
import itmo.polikiss.dao.KittyDaoImp;
import itmo.polikiss.dao.OwnerDao;
import itmo.polikiss.dao.OwnerDaoImp;
import itmo.polikiss.login.LoginConsole;
import itmo.polikiss.models.Owner;
import itmo.polikiss.services.KittyService;
import itmo.polikiss.services.OwnerService;
import picocli.CommandLine;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        OwnerDao ownerDao = new OwnerDaoImp();
        KittyDao kittyDao = new KittyDaoImp();
        KittyService kittyService = new KittyService(kittyDao);
        OwnerService ownerService = new OwnerService(ownerDao, kittyService);
        LoginConsole consoleLogin = new LoginConsole(ownerService, kittyService);
        LocalDate bday = LocalDate.parse("2009-01-01");
        consoleLogin.createOwner("l", bday);
        for (Owner allOwner : ownerService.findAllOwners()) {
            System.out.println(allOwner.getName());
        }

        while (true) {
            System.out.println("1. loginKitty \n2. loginOwner \n3. createOwner");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            int exitCode = new CommandLine(consoleLogin).execute(input.split(" "));
        }
    }
}