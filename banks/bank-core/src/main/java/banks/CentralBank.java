package banks;

import accounts.Account;
import exceptions.TransactionException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CentralBank {
    private List<Bank> banks = new ArrayList<>();
    private int nextAccountNumber;

    public CentralBank() {
        nextAccountNumber = 1;
    }

    /**
     * notify all banks for start of the month
     */
    public void notifyStartOfMonth() {
        if (LocalDate.now().getDayOfMonth() == 1) {
            banks.forEach(bank -> {
                bank.chargeInterestOnBalance();
                bank.fee();
            });
        }

    }

    public int getNextAccountNumber() {
        nextAccountNumber++;
        return nextAccountNumber;
    }

    /**
     * find account in all banks by it number
     *
     * @param accountNumber number of account
     * @return requested account
     */
    public Account findAccount(int accountNumber) {
        for (Bank bank : banks) {
            Account account = bank.findAccountInThisBank(accountNumber);
            if (account != null) {
                return account;
            }
        }
        throw new TransactionException("There is not such account");
    }

    /**
     * create new bank
     *
     * @param name name of the bank
     * @param interest of the bank
     * @param limit of the bank
     * @param commission of the bank
     * @param doubtfulAccountLimit of the bank
     * @param depositAccountMonth of the bank
     * @return new bank
     */
    public Bank createBank(String name, double interest, double limit, double commission, double doubtfulAccountLimit, long depositAccountMonth) {
        Bank bank = new Bank(this, name, interest, limit, commission, doubtfulAccountLimit, depositAccountMonth);
        banks.add(bank);
        return bank;
    }

    /**
     * find bank by it name
     *
     * @param name name of the bank
     */
    public Bank findBankByName(String name) {
        for (Bank bank : banks) {
            if (bank.getName().equals(name)) {
                return bank;
            }
        }
        throw new TransactionException(String.format("There are no %s bank", name));
    }
}
