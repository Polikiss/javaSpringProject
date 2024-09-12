package transactions;

import accounts.Account;
import exceptions.TransactionException;

public class TransferTransaction implements Transaction{
    private final Account accountReceiver;
    private final Account accountSender;
    private final double amount;
    private final int transactionNumber;
    private TransactionState state;
    public TransferTransaction(Account accountSender, Account accountReceiver, double amount, int transactionNumber){
        this.accountReceiver = accountReceiver;
        this.accountSender = accountSender;
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
        accountSender.transfer(amount, accountReceiver);
        state = TransactionState.IS_COMPLETED;
    }

    @Override
    public void undo() {
        if (state != TransactionState.IS_COMPLETED){
            throw new TransactionException("You can't undo this transaction");
        }
        accountReceiver.transfer(amount, accountSender);
        state = TransactionState.IS_UNDO;
    }
}
