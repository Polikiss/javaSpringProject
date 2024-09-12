package accounts;

import exceptions.TransactionException;

import java.time.LocalDate;

public class DepositAccount extends AccountImp{
    public DepositAccount(int accountNumber, double interest, double commision, double limit, LocalDate termAccount, LocalDate today) {
        super(accountNumber, interest, commision, limit, termAccount, today);
    }


    @Override
    public void withdrawal(double amount) {
        if(getTermAccount().isAfter(getToday())){
            throw new TransactionException(String.format("You can't withdrawal your balance before (%s)", getTermAccount()));
        }
        balance -= amount;
    }

    @Override
    public void transfer(double amount, Account account) {
        if(getTermAccount().isAfter(getToday())){
            throw new TransactionException(String.format("You can't transfer money before (%s)", getTermAccount()));
        }
        this.withdrawal(amount + getCommission());
        account.replenishment(amount);
    }
}
