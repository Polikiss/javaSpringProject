package itmo.polikiss.consoleLogins;

import itmo.polikiss.bankConsoles.BankConsole;
import banks.Bank;
import banks.CentralBank;
import itmo.polikiss.centralBankConsoles.CentralBankConsole;
import clients.Client;
import itmo.polikiss.userConsoles.ClientConsole;
import picocli.CommandLine;

import java.util.Scanner;

/**
 * login as bank ventral bank or client
 */
@CommandLine.Command(name = "login")
public class ConsoleLogin implements Runnable {
    private final CentralBankConsole centralBankConsole;
    private final CentralBank centralBank;
    private BankConsole bankConsole;
    private ClientConsole clientConsole;

    public ConsoleLogin(CentralBank centralBank) {
        this.centralBankConsole = new CentralBankConsole(centralBank);
        this.centralBank = centralBank;
    }

    @CommandLine.Command(name = "centralBank", description = "Login as central bank")
    public void centralBank() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. CreateBank\n2. exit");
        String input = scanner.nextLine();
        while (!input.equals("exit")) {
            int exitCode = new CommandLine(centralBankConsole).execute(input.split(" "));
            System.out.println("1. CreateBank\n2. exit");
            input = scanner.nextLine();
        }
    }

    @CommandLine.Command(name = "bank", description = "Login as bank")
    public void bank(@CommandLine.Parameters(index = "0", description = "name of the bank") String name) {
        Bank bank = centralBank.findBankByName(name);
        String input = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. createClient\n2. countInterestOnBalance\n3. setCommission\n4. setLimit\n5. setInterest\n6. exit");
        input = scanner.nextLine();
        while (!input.equals("exit")) {
            bankConsole = new BankConsole(bank);
            System.out.println("1. createClient\n2. countInterestOnBalance\n3. exit");
            input = scanner.nextLine();
        }
    }

    @CommandLine.Command(name = "client", description = "Login as client")
    public void client(@CommandLine.Parameters(index = "0", description = "name of the bank") String bankName,
                       @CommandLine.Parameters(index = "1", description = "name of the client") String name,
                       @CommandLine.Parameters(index = "2", description = "surname of the client") String surname) {
        Bank bank = centralBank.findBankByName(bankName);
        Client client = bank.findClientInThisBank(name, surname);
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. CreateCreditAccount\n2. CreateDebitAccount\n3. CreateDepositAccount\n4. withdrawal\n5. replenishment\n6. transfer\n7. UndoTransaction\n8. exit");
        String input = scanner.nextLine();
        while (!input.equals("exit")) {
            clientConsole = new ClientConsole(client, bank);
            int exitCode = new CommandLine(clientConsole).execute(input.split(" "));
            System.out.println("1. CreateCreditAccount\n2. CreateDebitAccount\n3. CreateDepositAccount\n4. withdrawal\n5. replenishment\n6. transfer\n7. UndoTransaction\n8. exit");
            input = scanner.nextLine();
        }
    }

    @Override
    public void run() {

    }
}
