package com.marie.bank.service;

import com.marie.bank.enumeration.TypeOperation;
import com.marie.bank.exception.AmountGreaterThanBalanceException;
import com.marie.bank.exception.NegativeAmountException;
import com.marie.bank.exception.NotAllowedOperationException;
import com.marie.bank.model.Account;
import com.marie.bank.model.Operation;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author marie
 */
public interface AccountService {

    public final static BigDecimal AUTHORISED_AMOUNT_DEPOSIT = new BigDecimal(10000);
    public final static BigDecimal AUTHORISED_AMOUNT_WITHDRAWAL = new BigDecimal(500);

    public default Optional<BigDecimal> deposit(Account account, Operation operation) {
        if (Optional.ofNullable(account).isPresent() && Optional.ofNullable(operation).isPresent()) {
            if (operation.getAmount().compareTo(BigDecimal.ZERO) == -1) {
                throw new NegativeAmountException("Amount must be positive");
            } else if (operation.getAmount().compareTo(AUTHORISED_AMOUNT_DEPOSIT) == 1) {
                throw new NotAllowedOperationException("Amount is greater than authorised amount on deposit");
            }
            account.setBalance(account.getBalance().add(operation.getAmount()));
            operation.setBalance(account.getBalance());
            operation.setType(TypeOperation.Deposit);
            account.getOperations().add(operation);
            return Optional.of(account.getBalance());
        } else {
            return Optional.empty();
        }
    }

    public default Optional<BigDecimal> withdrawal(Account account, Operation operation) {
        if (Optional.ofNullable(account).isPresent() && Optional.ofNullable(operation).isPresent()) {
            if (operation.getAmount().compareTo(BigDecimal.ZERO) == -1) {
                throw new NegativeAmountException("Amount must be positive");
            } else if (operation.getAmount().compareTo(AUTHORISED_AMOUNT_WITHDRAWAL) == 1) {
                throw new NotAllowedOperationException("Amount is greater than authorised amount on withdrawal");
            } else if (operation.getAmount().compareTo(account.getBalance()) == 1) {
                throw new AmountGreaterThanBalanceException("Amount can not be greater than the balance ");
            }

            account.setBalance(account.getBalance().subtract(operation.getAmount()));
            operation.setBalance(account.getBalance());
            operation.setType(TypeOperation.Withdrawal);
            account.getOperations().add(operation);
            return Optional.of( account.getBalance());
        } else {
            return Optional.empty();

        }
    }

    public default Optional<List<Operation>> historical(Account account) {
        if (Optional.ofNullable(account).isPresent()) {
            return Optional.of(account.getOperations());
        } else {
            return Optional.empty();
        }
    }
}
