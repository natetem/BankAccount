package com.marie.bank.service;

import com.marie.bank.model.Account;
import com.marie.bank.model.Operation;
import com.marie.bank.repository.AccountRepository;
import com.marie.bank.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marie
 */
@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Operation depositOperation(Operation operation) {
        Account account = operation.getAccount();
        account.deposit(operation.getAmount());
        accountRepository.save(account);
        return operationRepository.save(operation);
    }

    public Operation getOperation(Long id) {
        return operationRepository.findOne(id);
    }

}
