package banks;

import accounts.*;
import clients.Client;
import clients.ClientBuilder;
import clients.ClientState;
import exceptions.TransactionException;
import transactions.ReplenishmentTransaction;
import transactions.Transaction;
import transactions.TransferTransaction;
import transactions.WithdrawalTransaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * describe regular bank
 */

public class Bank {
    private final List<Client> clients = new ArrayList<>();
    private final List<Account> accounts = new ArrayList<>();
    private final CentralBank centralBank;
    private final String name;
    private double interest;
    private double limit;

    private double doubtfulAccountLimit;
    private double commission;
    private long depositAccountMonth;
    private LocalDate localDate;

    public Bank(CentralBank centralBank, String name, double interest, double limit, double commission, double doubtfulAccountLimit, long depositAccountMonth) {
        this.name = name;
        this.centralBank = centralBank;
        this.interest = interest;
        this.commission = commission;
        this.limit = limit;
        this.doubtfulAccountLimit = doubtfulAccountLimit;
        this.depositAccountMonth = depositAccountMonth;
        this.localDate = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public double getDoubtfulAccountLimit() {
        return doubtfulAccountLimit;
    }

    public void setInterest(double interest) {
        this.interest = interest;
        String message = String.format("Your interest was changed to %s", interest);
        notify(message);

    }

    public void setLimit(double limit) {
        this.limit = limit;
        String message = String.format("Your limit was changed to %s", limit);
        notify(message);
    }

    public void setCommission(double commission) {
        this.commission = commission;
        String message = String.format("Your commission was changed to %s", commission);
        notify(message);
    }


    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * send clients notification of changes
     *
     * @param message message for client about bank update
     */
    public void notify(String message) {
        clients.forEach(client -> client.update(message));
    }

    /**
     * create new client of this bank
     *
     * @param name name of client
     * @param surname surname of client
     * @param address address if client
     * @param passportNumber passport number of client
     * @return new client
     */

    public Client createClient(String name, String surname, String address, String passportNumber) {
        var clientBuilder = new ClientBuilder(this);
        clientBuilder.withName(name);
        clientBuilder.withSurname(surname);
        if (!address.isEmpty()) {
            clientBuilder.withAddress(address);
        }
        if (!passportNumber.isEmpty()) {
            clientBuilder.withPassport(passportNumber);
        }
        Client client = clientBuilder.сreateClient();
        clients.add(client);
        return client;
    }

    /**
     * create new deposit account for client
     *
     * @param client owner of new deposit account
     * @return new deposit account
     */
    public Account createDepositAccount(Client client) {
        int accountNumber = centralBank.getNextAccountNumber();
        Account account = new DepositAccount(accountNumber, interest, 0, 0, LocalDate.now().plusMonths(depositAccountMonth), LocalDate.now());
        if (client.getState() == ClientState.DOUBTFUL) {
            account = new DoubtfulAccount(account, getDoubtfulAccountLimit());
        }
        accounts.add(account);
        return account;
    }

    /**
     * create new debit account for client
     *
     * @param client owner of debit account
     * @return new debit account
     */
    public Account createDebitAccount(Client client) {
        int accountNumber = centralBank.getNextAccountNumber();
        Account account = new DebitAccount(accountNumber, interest, 0, 0, LocalDate.now());
        if (client.getState() == ClientState.DOUBTFUL) {
            account = new DoubtfulAccount(account, getDoubtfulAccountLimit());
        }
        accounts.add(account);
        return account;
    }

    /**
     * create new credit account for client
     *
     * @param client owner of credit account
     * @return new credit account
     */
    public Account createCreditAccount(Client client) {
        int accountNumber = centralBank.getNextAccountNumber();
        Account account = new CreditAccount(accountNumber, interest, commission, limit, LocalDate.now());
        if (client.getState() == ClientState.DOUBTFUL) {
            account = new DoubtfulAccount(account, getDoubtfulAccountLimit());
        }
        accounts.add(account);
        return account;
    }

    /**
     * replenishment of account balance for amount money
     *
     * @param account account that is replenished
     * @param amount amount money to replenish
     */
    public void replenishment(Account account, double amount) {
        Transaction transaction = new ReplenishmentTransaction(account, amount, account.getTransactionHistory().size());
        transaction.execute();
        account.addToTransactionHistory(transaction);
    }

    /**
     * withdrawal of account balance for amount money
     *
     * @param account account that is withdrawed
     * @param amount amount money to withdraw
     */
    public void withdrawal(Account account, double amount) {
        Transaction transaction = new WithdrawalTransaction(account, amount, account.getTransactionHistory().size());
        transaction.execute();
        account.addToTransactionHistory(transaction);
    }

    /**
     * transfer amount of money from account Sender to account receiver
     *
     * @param accountReceiverNumber number of account receiver
     * @param accountSender   account sender
     * @param amount          amount of money to transfer
     */
    public void transfer(int accountReceiverNumber, Account accountSender, double amount) {
        Account accountReceiver = findAccountInAllBanks(accountReceiverNumber);
        Transaction transaction = new TransferTransaction(accountSender, accountReceiver, amount, accountSender.getTransactionHistory().size());
        transaction.execute();
        accountSender.addToTransactionHistory(transaction);
    }

    /**
     * undo transaction of account
     *
     * @param account account that made this transaction
     */

    public void undoTransaction(Account account, int transactionNumber) {
        var history = account.getTransactionHistory();
        if(history.size() <= transactionNumber){
            throw new TransactionException("Transaction does noe exist");
        }
        var transaction = history.get(transactionNumber);
        transaction.undo();
    }

    public void fee() {
        accounts.forEach(Account::fee);
    }

    public void countInterestOnBalance() {
        accounts.forEach(Account::interestOnBalance);
    }

    public void chargeInterestOnBalance() {
        accounts.forEach(account -> {
            account.replenishment(account.getInterestOnBalance());
            account.setInterestOnBalance(0);
            account.setToday(account.getToday().plusDays(1));
        });
    }

    /**
     * find client in this bank by name and surname
     *
     * @param name name of the client
     * @param surname surname of the client
     * @return client
     */
    public Client findClientInThisBank(String name, String surname) {
        for (Client client : clients) {
            if (client.getName().equals(name) && client.getSurname().equals(surname)) {
                return client;
            }
        }
        throw new TransactionException("Can't find client");
    }

    /**
     * find account in this bank by account number
     *
     * @param accountNumber number of account
     * @return requested account or null
     */

    public Account findAccountInThisBank(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    /**
     * find account in all bank by account number
     *
     * @param accountNumber number of account
     * @return requested account
     */

    public Account findAccountInAllBanks(int accountNumber) {
        return centralBank.findAccount(accountNumber);
    }
}
