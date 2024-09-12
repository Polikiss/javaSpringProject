package itmo.polikiss.consoleLogins;

import banks.CentralBank;
import picocli.CommandLine;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CentralBank centralBank = new CentralBank();
        ConsoleLogin consoleLogin = new ConsoleLogin(centralBank);
        while (true) {
            System.out.println("1. centralBank \n2. bank \n3. client");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            int exitCode = new CommandLine(consoleLogin).execute(input.split(" "));
        }
    }


}
