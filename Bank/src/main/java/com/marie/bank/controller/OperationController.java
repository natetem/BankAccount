package com.marie.bank.controller;

import com.marie.bank.model.Account;
import com.marie.bank.model.Operation;
import com.marie.bank.repository.AccountRepository;
import com.marie.bank.repository.OperationRepository;
import com.marie.bank.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marie
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Operation getOperation(@PathVariable Long id) {
        return operationService.getOperation(id);
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public Operation depositOperation(@ModelAttribute Operation operation) {
//        System.out.println("operation"+operationbank.getAmount());
//        System.out.println("operation Account"+operationbank.getAccount());
//        return operationService.depositOperation(operationbank);
//        Account account = operation.getAccount();
//        account.deposit(operation.getAmount());
       
        return operationRepository.save(operation);
    }

}
