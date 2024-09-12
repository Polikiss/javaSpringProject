package accounts;

import transactions.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountImp implements Account {
    private final List<Transaction> transactionHistory = new ArrayList<>();
    private final int accountNumber;
    protected double commission;
    protected double interest;
    protected double balance;
    protected LocalDate termAccount;
    private double limit;
    private double interestOnBalance;

    public LocalDate getToday() {
        return today;
    }
    public void setToday(LocalDate localDate){
        this.today = localDate;
    }


    private LocalDate today;

    public AccountImp(int accountNumber, double interest, double commission, double limit, LocalDate termAccount, LocalDate today) {
        this.accountNumber = accountNumber;
        this.termAccount = termAccount;
        this.limit = limit;
        this.interest = interest;
        this.commission = commission;
        this.today = today;
        balance = 0;
        interestOnBalance = 0;
    }

    public double getInterestOnBalance() {
        return interestOnBalance;
    }

    @Override
    public void setInterestOnBalance(double amount) {
        interestOnBalance = amount;
    }

    public void addToTransactionHistory(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    @Override
    public void setCommission(double commission) {
        this.commission = commission;
    }

    @Override
    public void setLimit(double limit) {
        this.limit = limit;
    }

    @Override
    public void setInterest(double interest) {
        this.interest = interest;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void setTermAccount(LocalDate termAccount) {
        this.termAccount = termAccount;
    }

    @Override
    public double getCommission() {
        return commission;
    }

    @Override
    public double getInterest() {
        return interest;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getLimit() {
        return limit;
    }

    @Override
    public LocalDate getTermAccount() {
        return termAccount;
    }

    @Override
    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public void replenishment(double amount) {

        balance += amount;
    }

    @Override
    public void withdrawal(double amount) {
        balance -= amount;
    }

    @Override
    public void transfer(double amount, Account account) {
        withdrawal(amount + getCommission());
        account.replenishment(amount);
    }

    public void fee() {
        balance -= commission;
    }

    @Override
    public void interestOnBalance() {
        interestOnBalance += getBalance() * getInterest() / 365;
    }
}
