package com.marie.bank.service;

import com.marie.bank.exception.AmountGreaterThanBalanceException;
import com.marie.bank.exception.NegativeAmountException;
import com.marie.bank.model.Account;
import com.marie.bank.model.Operation;
import com.marie.bank.model.TypeOperation;
import com.marie.bank.repository.AccountRepository;
import com.marie.bank.repository.OperationRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marie
 */
@Service
public class OperationService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OperationRepository operationRepository;

    public boolean depositOperation(int id, double amount) throws NegativeAmountException {
        Account account = accountRepository.findOne(id);
        account.deposit(amount);
        Operation operation = new Operation(TypeOperation.Deposit, new Date(), account.getBalance(), amount, account);
        operationRepository.save(operation);
        accountRepository.save(account);
        return true;
    }

    public boolean withdrawalOperation(int id, double amount) throws NegativeAmountException, AmountGreaterThanBalanceException {
        Account account = accountRepository.findOne(id);
        account.withdrawal(amount);
        Operation operation = new Operation(TypeOperation.Withdrawal, new Date(), account.getBalance(), amount, account);
        operationRepository.save(operation);
        accountRepository.save(account);
        return true;
    }

}
