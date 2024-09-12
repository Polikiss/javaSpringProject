package accounts;

import exceptions.TransactionException;

import java.time.LocalDate;

public class CreditAccount extends AccountImp {
    public CreditAccount(int accountNumber, double interest, double commission, double limit, LocalDate today) {
        super(accountNumber, interest, commission, limit, today, today);
    }

    @Override
    public void withdrawal(double amount) {
        if (-(getBalance() - amount) > getLimit()) {
            throw new TransactionException("You gone beyond your credit limits");
        }
        setBalance(getBalance() - amount);
    }
}
