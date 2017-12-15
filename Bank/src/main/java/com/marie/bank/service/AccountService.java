package com.marie.bank.service;

import com.marie.bank.enumeration.TypeOperation;
import com.marie.bank.exception.NotAllowedOperationException;
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

    public final static double AUTHORISED_AMOUNT_DEPOSIT = 10000;
    public final static double AUTHORISED_AMOUNT_WITHDRAWAL = 500;

    public void deposit(Account account, Operation operation) {
        if (operation.getAmount() < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        } else if (operation.getAmount() > AUTHORISED_AMOUNT_DEPOSIT) {
            throw new NotAllowedOperationException("Amount is greater than authorised amount on deposit");
        }
        account.setBalance(account.getBalance() + operation.getAmount());
        operation.setBalance(account.getBalance());
        operation.setType(TypeOperation.Deposit);
        account.getOperations().add(operation);
    }

    public void withdrawal(Account account, Operation operation) {
        if (operation.getAmount() < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        } else if (operation.getAmount() > account.getBalance()) {
            throw new NotAllowedOperationException("Amount can not be greater than the balance ");
        } else if (operation.getAmount() > AUTHORISED_AMOUNT_WITHDRAWAL) {
            throw new NotAllowedOperationException("Amount is greater than authorised amount on withdrawal");
        }

        account.setBalance(account.getBalance() - operation.getAmount());
        operation.setBalance(account.getBalance());
        operation.setType(TypeOperation.Withdrawal);
        account.getOperations().add(operation);
    }

    public void historical(Account account) {

        List<String> result = account.getOperations().stream()
                .sorted(Comparator.comparing(Operation::getDate).reversed())
                .map(Operation::toString).collect(Collectors.toList());

        result.forEach(System.out::println);
    }

}
