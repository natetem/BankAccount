package com.marie.bank.controller;

import com.marie.bank.model.Account;
import com.marie.bank.model.Client;
import com.marie.bank.model.Operation;
import com.marie.bank.service.AccountService;
import com.marie.bank.service.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marie
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/accounts")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;
    
    @RequestMapping(value = "/operation/{id}", method = RequestMethod.GET)
    public Operation findOperation(@PathVariable Long id) {
        return accountService.getOperation(id);
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Account> findAllAccounts() {
        return accountService.findAll();
    }

    @RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
    public List<Account> findAccountsByClient(@PathVariable int id) {
        Client client = clientService.getClient(id);
        return accountService.findAccountsByClient(client);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable int id) {
        return accountService.getAccount(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteAccount(@PathVariable int id) {
        accountService.deleteAccount(id);
        return true;
    }

    @RequestMapping(value = "/deposit",method = RequestMethod.POST)
    public Account depositOnAccount(@RequestParam int id, @RequestParam double amount) {
        return accountService.depositOperation(id, amount);
    }
    
    @RequestMapping(value = "/withdrawal",method = RequestMethod.POST)
    public Account withdrawalOnAccount(@RequestParam int id, @RequestParam double amount) {
        return accountService.withdrawalOperation(id, amount);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Account createAccount(@ModelAttribute Account account) {
        return accountService.createAccount(account);
    }

}
