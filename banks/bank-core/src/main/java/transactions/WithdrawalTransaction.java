package transactions;

import accounts.Account;
import exceptions.TransactionException;

public class WithdrawalTransaction implements Transaction{
    private final Account account;
    private final double amount;
    private final int transactionNumber;
    private TransactionState state;
    public WithdrawalTransaction(Account account, double amount, int transactionNumber){
        this.account = account;
        this.amount = amount;
        this.state = TransactionState.IN_PROCESS;
        this.transactionNumber = transactionNumber;
    }

    @Override
    public int getTransactionNumber() {
        return transactionNumber;
    }

    @Override
    public void execute() {
        account.withdrawal(amount);
        state = TransactionState.IS_COMPLETED;
    }

    @Override
    public void undo() {
        if (state != TransactionState.IS_COMPLETED){
            throw new TransactionException("You can't undo transaction");
        }
        account.replenishment(amount);
        state = TransactionState.IS_UNDO;
    }
}
