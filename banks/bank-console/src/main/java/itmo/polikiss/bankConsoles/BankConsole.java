package itmo.polikiss.bankConsoles;

import banks.Bank;
import picocli.CommandLine;

/**
 * console for bank
 */

@CommandLine.Command(name = "bank")
public class BankConsole {
    private final Bank bank;

    public BankConsole(Bank bank) {
        this.bank = bank;
    }

    @CommandLine.Command(name = "createClient", description = "Create new client")
    public void createClient(@CommandLine.Parameters(index = "0", description = "First name of client") String name,
                             @CommandLine.Parameters(index = "1", description = "Surname of client") String surname,
                             @CommandLine.Parameters(index = "2", defaultValue = "", description = "address of client") String address,
                             @CommandLine.Parameters(index = "3", defaultValue = "", description = "passport number of client") String passportNumber) {
        bank.createClient(name, surname, address, passportNumber);
        System.out.printf("Client %s %s in system%n", name, surname);
    }

    @CommandLine.Command(name = "countInterestOnBalance", description = "count daily interest on balance")
    public void countInterestOnBalance() {
        bank.countInterestOnBalance();
        System.out.println("Success");
    }

    @CommandLine.Command(name = "setInterest", description = "set interest on balance")
    public void setInterest(@CommandLine.Parameters(index = "0", description = "Interest on Balance") double interest) {
        bank.setInterest(interest);
        System.out.println("Success");
    }

    @CommandLine.Command(name = "setLimit", description = "set credit limit")
    public void setLimit(@CommandLine.Parameters(index = "0", description = "credit limit") double limit) {
        bank.setLimit(limit);
        System.out.println("Success");
    }

    @CommandLine.Command(name = "setCommission", description = "set commission")
    public void setCommission(@CommandLine.Parameters(index = "0", description = "credit limit") double commission) {
        bank.setCommission(commission);
        System.out.println("Success");
    }

}
