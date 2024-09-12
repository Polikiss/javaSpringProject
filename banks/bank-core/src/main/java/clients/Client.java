package clients;

import accounts.Account;
import banks.Bank;
import exceptions.TransactionException;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private final List<Account> accounts = new ArrayList<>();
    private final List<String> notifications = new ArrayList<>();
    private String name;
    private String surname;

    private String address;

    private String passportNumber;
    private ClientState state;
    private Bank bank;

    public Client(Bank bank, String name, String surname, String address, String passportNumber) {
        this.bank = bank;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.passportNumber = passportNumber;
        this.state = ClientState.DOUBTFUL;
    }

    public String getName() {
        return name;
    }


    public String getSurname() {
        return surname;
    }

    public ClientState getState() {
        return state;
    }
    public void setAddress(String address) {
        this.address = address;
        changeClientState();
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
        changeClientState();
    }

    public void changeClientState() {
        if (address != null && passportNumber != null) {
            state = ClientState.NOT_DOUBTFUL;
        }
    }

    public void update(String message) {
        notifications.add(message);
    }

    public void replenishment(double amount, int accountNumber) {
        Account account = findClientAccount(accountNumber);
        bank.replenishment(account, amount);
    }

    public void withdrawal(double amount, int accountNumber) {
        Account account = findClientAccount(accountNumber);
        bank.withdrawal(account, amount);
    }

    public void transfer(double amount, int senderAccountNumber, int receiverAccountNumber) {
        Account sender = findClientAccount(senderAccountNumber);
        bank.transfer(receiverAccountNumber, sender, amount);
    }

    public void undoTransaction(int accountNumber, int transactionNumber) {
        Account account = findClientAccount(accountNumber);
        bank.undoTransaction(account, transactionNumber);
    }

    /**
     * show all client Notifications
     *
     * @return list of  Notifications
     */
    public List<String> showNotifications() {
        return notifications;
    }

    /**
     * show all client accounts
     *
     * @return list of accounts
     */
    public List<Integer> getAccountsNumbers() {
        return accounts.stream().map(Account::getAccountNumber).toList();

    }

    /**
     * fins client account by it account number
     *
     * @param accountNumber number of account
     * @return requested account
     */
    public Account findClientAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        throw new TransactionException("Can't find account");
    }

}
