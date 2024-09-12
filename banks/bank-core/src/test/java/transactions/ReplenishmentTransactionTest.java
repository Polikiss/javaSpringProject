package transactions;

import accounts.Account;
import accounts.CreditAccount;
import accounts.DebitAccount;
import exceptions.TransactionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReplenishmentTransactionTest {
    @Test
    public void ReplenishmentUndo() {
        Account debitAccount = new DebitAccount(1, 3, 1, 2, LocalDate.now());
        Transaction transaction = new ReplenishmentTransaction(debitAccount , 200, 1);
        transaction.execute();
        Assertions.assertEquals(200, debitAccount .getBalance());;
        transaction.undo();
        Assertions.assertThrows(TransactionException.class, () -> transaction.undo());
    }

}