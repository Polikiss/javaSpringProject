package transactions;

import exceptions.TransactionException;

/**
 * describe transaction
 */

public interface Transaction {
    int getTransactionNumber();
    /**
     * execute transaction
     */
    void execute();

    /**
     * undo transaction
     */
    void undo();
}
