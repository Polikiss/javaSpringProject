package accounts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import exceptions.TransactionException;

import java.time.LocalDate;

class CreditAccountTest {
    @Test
    public void WithdrawalLimit() {
        CreditAccount creditAccount = new CreditAccount(1, 3, 1, 50, LocalDate.now());
        Assertions.assertThrows(TransactionException.class, () -> creditAccount.withdrawal(200));
    }
}