package accounts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import timeMachine.TimeMachine;
import exceptions.TransactionException;

import java.time.LocalDate;

class DepositAccountTest {
    @Test
    public void withdrawalLimit() {
        DepositAccount depositAccount = new DepositAccount(1, 3, 1, 50, LocalDate.now().plusMonths(2), LocalDate.now());
        Assertions.assertThrows(TransactionException.class, () -> depositAccount.withdrawal(200));
    }
    @Test
    public void replenishment(){
        DepositAccount depositAccount = new DepositAccount(1, 3, 1, 50, LocalDate.now().plusMonths(2), LocalDate.now());
        depositAccount.replenishment(20);
        Assertions.assertEquals(depositAccount.getBalance(), 20);
    }

    @Test
    public void failTransfer(){
        DepositAccount depositAccount = new DepositAccount(1, 3, 1, 50, LocalDate.now().plusMonths(2), LocalDate.now());
        depositAccount.replenishment(20);
        Account account = new AccountImp(1, 3, 1, 50, LocalDate.now().plusMonths(2), LocalDate.now());
        Assertions.assertThrows(TransactionException.class, () -> depositAccount.transfer(10, account));
    }
  @Test
  public void successTransfer(){
        DepositAccount depositAccount = new DepositAccount(1, 3, 1, 50, LocalDate.now().plusMonths(2), LocalDate.now());
        depositAccount.replenishment(20);
        Account account = new AccountImp(1, 3, 1, 50, LocalDate.now().plusMonths(2), LocalDate.now());
        TimeMachine timeMachine = new TimeMachine();
        timeMachine.rewindAccount(depositAccount, 1000);
        Assertions.assertDoesNotThrow(() -> depositAccount.transfer(10, account));
        Assertions.assertEquals(10, account.getBalance());
        Assertions.assertEquals(9, depositAccount.getBalance());
    }
}