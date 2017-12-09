package com.marie.bank.service;

import com.marie.bank.exception.AmountGreaterThanBalanceException;
import com.marie.bank.exception.NegativeAmountException;
import com.marie.bank.model.Account;
import com.marie.bank.model.Client;
import com.marie.bank.model.Operation;
import com.marie.bank.repository.AccountRepository;
import com.marie.bank.repository.OperationRepository;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marie
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OperationRepository operationRepository;
    
    public Operation getOperation(Long id) {
        return operationRepository.findOne(id);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccount(int id) {
        return accountRepository.findOne(id);
    }

    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(int id) {
        accountRepository.delete(id);
    }

    public Account depositOperation(int id, double amount) {
        try {
            Account account = accountRepository.findOne(id);
            Date date=new Date();
            account.deposit(amount);
             Operation operation= new Operation("Deposit",date, account.getBalance(), amount, account);
            operationRepository.save(operation);
            return accountRepository.save(account);
        } catch (NegativeAmountException ex) {
            return null;
        }
    }
    
     public Account withdrawalOperation(int id, double amount) {
        try {
            Account account = accountRepository.findOne(id);
            Date date=new Date();          
            account.withdrawal(amount);
            Operation operation= new Operation("Withdrawal", date, account.getBalance(), amount,account);
            operationRepository.save(operation);
            return accountRepository.save(account);
        } catch (NegativeAmountException | AmountGreaterThanBalanceException ex) {
            return null;
        }
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public List<Account> findAccountsByClient(Client client) {
        return accountRepository.findAccountsByClient(client);
    }
}
