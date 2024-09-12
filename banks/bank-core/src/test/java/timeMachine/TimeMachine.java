package timeMachine;

import accounts.Account;
import banks.Bank;

public class TimeMachine {
    public void rewindBank(Bank bank, int amountDays) {
        for (int i = 0; i < amountDays; i++) {
            bank.countInterestOnBalance();
            if (bank.getLocalDate().getDayOfMonth() == 1) {
                bank.fee();
                bank.chargeInterestOnBalance();
            }
            bank.setLocalDate(bank.getLocalDate().plusDays(1));
        }
    }

    public void rewindAccount(Account account, int amountDays) {
        for (int i = 0; i < amountDays; i++) {
            account.interestOnBalance();
            account.setTermAccount(account.getTermAccount().minusDays(1));
        }
    }
}
