package accounts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import exceptions.TransactionException;

import java.time.LocalDate;

class DoubtfulAccountTest {

    @Test
    public void withdrawalBelowLimit() {
        Account debitAccount = new DebitAccount(1, 2, 4, 5, LocalDate.now());
        var account = new DoubtfulAccount(debitAccount, 2);
        Assertions.assertThrows(TransactionException.class, () -> account.withdrawal(3));
    }
    @Test
    public void replenishment(){
        DebitAccount debitAccount = new DebitAccount(1, 2, 4, 5, LocalDate.now());
        debitAccount.replenishment(20);
        Assertions.assertEquals(debitAccount.getBalance(), 20);
    }

}