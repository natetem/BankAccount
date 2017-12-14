package com.marie.bank.service;

import com.marie.bank.exception.AmountGreaterThanBalanceException;
import com.marie.bank.exception.NegativeAmountException;
import com.marie.bank.model.Account;
import com.marie.bank.model.Operation;

/**
 *
 * @author marie
 */
public class AccountService {

    public void deposit(Account account, Operation operation) throws NegativeAmountException {
        if (operation.getAmount() < 0) {
            throw new NegativeAmountException("Amount must be positive");
        }
        account.setBalance(account.getBalance() + operation.getAmount());
    }

    public void withdrawal(Account account, Operation operation) throws AmountGreaterThanBalanceException, NegativeAmountException {
        if (operation.getAmount() < 0) {
            throw new NegativeAmountException("Amount must be positive");
        } else if (operation.getAmount() > account.getBalance()) {
            throw new AmountGreaterThanBalanceException("Amount can not be greater than the balance ");
        }

        account.setBalance(account.getBalance() - operation.getAmount());
    }

}
