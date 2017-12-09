package com.marie.bank.service;

import com.marie.bank.model.Account;
import com.marie.bank.repository.AccountRepository;
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
    
    public Account getAccount(int id) {
        return accountRepository.findOne(id);
    }
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
}
