package com.marie.bank.service;

import com.marie.bank.exception.AmountGreaterThanBalanceException;
import com.marie.bank.exception.NegativeAmountException;
import com.marie.bank.model.Account;
import com.marie.bank.model.Operation;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        operation.setBalance(account.getBalance());
        account.getOperations().add(operation);
    }

    public void withdrawal(Account account, Operation operation) throws AmountGreaterThanBalanceException, NegativeAmountException {
        if (operation.getAmount() < 0) {
            throw new NegativeAmountException("Amount must be positive");
        } else if (operation.getAmount() > account.getBalance()) {
            throw new AmountGreaterThanBalanceException("Amount can not be greater than the balance ");
        }

        account.setBalance(account.getBalance() - operation.getAmount());
        operation.setBalance(account.getBalance());
        account.getOperations().add(operation);
    }

    public void historical(Account account) {

        List<String> result = account.getOperations().stream()
                .sorted(Comparator.comparing(Operation::getDate).reversed())
                .map(Operation::toString).collect(Collectors.toList());
        
        result.forEach(System.out::println);
    }

}
