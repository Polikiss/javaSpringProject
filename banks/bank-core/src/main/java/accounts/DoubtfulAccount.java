package accounts;

import exceptions.TransactionException;
import transactions.Transaction;

import java.time.LocalDate;
import java.util.List;

public class DoubtfulAccount implements Account {
    private Account account;
    private double limit;

    public DoubtfulAccount(Account account, double limit) {
        this.account = account;
        this.limit = limit;
    }

    @Override
    public void addToTransactionHistory(Transaction transaction) {
        account.addToTransactionHistory(transaction);
    }

    @Override
    public List<Transaction> getTransactionHistory() {
        return account.getTransactionHistory();
    }

    @Override
    public void setCommission(double commission) {
        account.setCommission(commission);
    }

    @Override
    public void setLimit(double limit) {
        account.setLimit(limit);
    }

    @Override
    public void setInterest(double interest) {
        account.setInterest(interest);
    }

    @Override
    public void setBalance(double balance) {
        account.setBalance(balance);
    }

    @Override
    public void setTermAccount(LocalDate termAccount) {
        account.setTermAccount(termAccount);
    }

    @Override
    public double getInterestOnBalance() {
        return account.getInterestOnBalance();
    }

    @Override
    public void setInterestOnBalance(double amount) {
        account.setInterestOnBalance(amount);
    }

    @Override
    public LocalDate getToday() {
        return account.getToday();
    }

    @Override
    public void setToday(LocalDate localDate) {
        account.setToday(localDate);
    }

    @Override
    public double getCommission() {
        return account.getCommission();
    }

    @Override
    public double getInterest() {
        return account.getInterest();
    }

    @Override
    public double getBalance() {
        return account.getBalance();
    }

    @Override
    public double getLimit() {
        return account.getLimit();
    }

    @Override
    public LocalDate getTermAccount() {
        return account.getTermAccount();
    }

    @Override
    public int getAccountNumber() {
        return account.getAccountNumber();
    }


    @Override
    public void withdrawal(double amount) {
        if (limit < amount) {
            throw new TransactionException("Fill your account to perform such transactions");
        }
        account.withdrawal(amount);
    }

    @Override
    public void replenishment(double amount) {
        account.replenishment(amount);
    }

    @Override
    public void transfer(double amount, Account recieverAccount) {
        if (limit < amount) {
            throw new TransactionException("Fill your account to perform such transactions");
        }
        account.transfer(amount, recieverAccount);
    }

    @Override
    public void fee() {
        account.fee();
    }

    @Override
    public void interestOnBalance() {
        account.interestOnBalance();

    }
}
