package com.marie.bank.service;

import com.marie.bank.enumeration.TypeOperation;
import com.marie.bank.exception.AmountGreaterThanBalanceException;
import com.marie.bank.exception.NegativeAmountException;
import com.marie.bank.exception.NotAllowedOperationException;
import com.marie.bank.model.Account;
import com.marie.bank.model.Operation;
import java.util.List;

/**
 *
 * @author marie
 */
public interface AccountService {

    public final static double AUTHORISED_AMOUNT_DEPOSIT = 10000;
    public final static double AUTHORISED_AMOUNT_WITHDRAWAL = 500;

    public default double deposit(Account account, Operation operation) {
        if (operation.getAmount() < 0) {
            throw new NegativeAmountException("Amount must be positive");
        } else if (operation.getAmount() > AUTHORISED_AMOUNT_DEPOSIT) {
            throw new NotAllowedOperationException("Amount is greater than authorised amount on deposit");
        }
        account.setBalance(account.getBalance() + operation.getAmount());
        operation.setBalance(account.getBalance());
        operation.setType(TypeOperation.Deposit);
        account.getOperations().add(operation);
        return account.getBalance();
    }

    public default double withdrawal(Account account, Operation operation) {
        if (operation.getAmount() < 0) {
            throw new NegativeAmountException("Amount must be positive");
        } else if (operation.getAmount() > AUTHORISED_AMOUNT_WITHDRAWAL) {
            throw new NotAllowedOperationException("Amount is greater than authorised amount on withdrawal");
        } else if (operation.getAmount() > account.getBalance()) {
            throw new AmountGreaterThanBalanceException("Amount can not be greater than the balance ");
        }

        account.setBalance(account.getBalance() - operation.getAmount());
        operation.setBalance(account.getBalance());
        operation.setType(TypeOperation.Withdrawal);
        account.getOperations().add(operation);
        return account.getBalance();
    }

    public default List<Operation> historical(Account account) {
        return account.getOperations();
    }

}
