package accounts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import exceptions.TransactionException;

import java.time.LocalDate;

class DebitAccountTest {
    @Test
    public void withdrawalBelowZero() {
        DebitAccount debitAccount = new DebitAccount(1, 2, 4, 5, LocalDate.now());
        Assertions.assertThrows(TransactionException.class, () -> debitAccount.withdrawal(200));
    }
    @Test
    public void replenishment(){
        DebitAccount debitAccount = new DebitAccount(1, 2, 4, 5, LocalDate.now());
        debitAccount.replenishment(20);
        Assertions.assertEquals(debitAccount.getBalance(), 20);
    }

    @Test
    public void failTransfer(){
        DepositAccount depositAccount = new DepositAccount(1, 3, 1, 50, LocalDate.now().plusMonths(2), LocalDate.now());
        depositAccount.replenishment(20);
        Account account = new AccountImp(1, 3, 1, 50, LocalDate.now().plusMonths(2), LocalDate.now());
        Assertions.assertThrows(TransactionException.class, () -> depositAccount.transfer(10, account));
    }

}