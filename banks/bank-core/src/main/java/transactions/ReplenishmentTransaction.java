package transactions;

import accounts.Account;
import exceptions.TransactionException;

public class ReplenishmentTransaction implements Transaction{
    private final Account account;
    private final double amount;
    private final int transactionNumber;
    private TransactionState state;
    public ReplenishmentTransaction(Account account, double amount, int transactionNumber){
        this.account = account;
        this.amount = amount;
        this.transactionNumber = transactionNumber;
        this.state = TransactionState.IN_PROCESS;
    }

    @Override
    public int getTransactionNumber() {
        return transactionNumber;
    }

    @Override
    public void execute() {
        account.replenishment(amount);
        state = TransactionState.IS_COMPLETED;
    }

    @Override
    public void undo() {
        if (!state.equals(TransactionState.IS_COMPLETED)){
            throw new TransactionException("You can't undo this transaction");
        }
        account.withdrawal(amount);
        state = TransactionState.IS_UNDO;
    }
}
