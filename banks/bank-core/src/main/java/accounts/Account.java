package accounts;

import exceptions.TransactionException;
import transactions.Transaction;

import java.time.LocalDate;
import java.util.List;
/**
 * Class describe base methods of accounts
 */

public interface Account {
    void addToTransactionHistory(Transaction transaction);
    List<Transaction> getTransactionHistory();
    void setCommission(double commission);
    void setLimit(double limit);
    void setInterest(double interest);
    void setBalance(double balance);
    void setTermAccount(LocalDate termAccount);
    double getInterestOnBalance();
    void setInterestOnBalance(double amount);
    public LocalDate getToday();
    public void setToday(LocalDate localDate);
    double getCommission();
    double getInterest();
    double getBalance();
    double getLimit();
    LocalDate getTermAccount();
    int getAccountNumber();
    /**
     * withdrawal of balance for amount money
     * @param amount money to withdrawal
     */
    void withdrawal(double amount);
    /**
     * replenishment of balance for amount money
     * @param amount amount money to replenish
     */
    void replenishment(double amount);
    /**
     transfer amount of money from this account to other account
     * @param amount  amount of money to transfer
     * @param account receiver account
     */
    void transfer(double amount, Account account) ;
    /**
     * fee commission for the use
     */
    void fee();
    /**
     * calculate daily interest on balance
     */
    void interestOnBalance();

}
