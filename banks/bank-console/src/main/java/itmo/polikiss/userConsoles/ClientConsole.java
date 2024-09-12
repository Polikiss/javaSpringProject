package itmo.polikiss.userConsoles;

import accounts.Account;
import banks.Bank;
import clients.Client;
import picocli.CommandLine;

/**
 * console for client
 */

@CommandLine.Command(name = "Client")
public class ClientConsole {
    private Client client;
    private Bank bank;

    public ClientConsole(Client client, Bank bank) {

        this.client = client;
        this.bank = bank;
    }

    @CommandLine.Command(name = "CreateCreditAccount", description = "Create new credit account")
    public void CreateCreditAccount() {
        bank.createCreditAccount(client);
        System.out.println("New credit account created");
    }

    @CommandLine.Command(name = "CreateDebitAccount", description = "Create new debit account")
    public void CreateDebitAccount() {
        bank.createDebitAccount(client);
        System.out.println("New debit account created");
    }

    @CommandLine.Command(name = "CreateDepositAccount", description = "Create new deposit account")
    public void CreateDepositAccount() {
        bank.createDepositAccount(client);
        System.out.println("New deposit account created");
    }

    @CommandLine.Command(name = "ShowNotifications", description = "Show all accounts notifications")
    public void ShowNotifications() {
        var notifications = client.showNotifications();
        notifications.forEach(System.out::println);
        System.out.println("Success");
    }

    @CommandLine.Command(name = "ShowAccountNumber", description = "Show all accounts numbers")
    public void showAccountsNumber() {
        var numbers = client.getAccountsNumbers();
        numbers.forEach(System.out::println);
        System.out.println("Success");
    }

    @CommandLine.Command(name = "withdrawal", description = "withdraw money from your account")
    public void withdrawal(@CommandLine.Parameters(index = "0", description = "amount of money") double amount,
                           @CommandLine.Parameters(index = "1", description = "number of account") int accountNumber) {
        client.withdrawal(amount, accountNumber);
        System.out.println("Success");
    }

    @CommandLine.Command(name = "replenishment", description = "replenishment money to your account")
    public void replenishment(@CommandLine.Parameters(index = "0", description = "amount of money") double amount,
                              @CommandLine.Parameters(index = "1", description = "number of account") int accountNumber) {
        client.replenishment(amount, accountNumber);
        System.out.println("Success");
    }

    @CommandLine.Command(name = "transfer", description = "transfer money from your account")
    public void transfer(@CommandLine.Parameters(index = "0", description = "amount of money") double amount,
                         @CommandLine.Parameters(index = "1", description = "number of sender account") int senderAccountNumber,
                         @CommandLine.Parameters(index = "2", description = "number of receiver account") int receiverAccountNumber) {
        client.transfer(amount, senderAccountNumber, receiverAccountNumber);
        System.out.println("Success");
    }

    @CommandLine.Command(name = "UndoTransaction", description = "Undo transaction of your account")
    public void UndoTransaction(@CommandLine.Parameters(index = "0", description = "number of account") int accountNumber,
                                @CommandLine.Parameters(index = "1", description = "number of account") int transactionNumber) {
        client.undoTransaction(accountNumber, transactionNumber);
        System.out.println("Success");
    }

    @CommandLine.Command(name = "setPassportNumber", description = "set Passport Number")
    public void setPassportNumber(@CommandLine.Parameters(index = "0", description = "set Passport Number") String passportNumber) {
        client.setPassportNumber(passportNumber);
        System.out.println("Success");
    }


}
