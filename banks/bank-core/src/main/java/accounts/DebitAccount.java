package accounts;

import exceptions.TransactionException;

import java.time.LocalDate;

public class DebitAccount extends AccountImp {

    public DebitAccount(int accountNumber, double interest, double commission, double limit, LocalDate today) {
        super(accountNumber, interest, commission, limit, today, today);
    }
    @Override
    public void withdrawal(double amount) {
        if (balance < amount){
            throw new TransactionException("insufficient funds in the account");
        }
        balance -= amount;
    }
}
