package banks;

import accounts.Account;
import accounts.DoubtfulAccount;
import clients.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BankTest {
    @Test
    public void CreateDoubtfulAccount() {
        CentralBank centralBank = new CentralBank();
        Bank bank = new Bank(centralBank,"ScroogeMcDuck", 1, 1,1,1, 1);
        Client client = bank.createClient("Polik", "Sleep", "moy adres ne dom i ne ylica", "");
        Account account = bank.createDebitAccount(client);
        Assertions.assertTrue(account instanceof DoubtfulAccount);
    }
}