package com.marie.bank.controller;

import com.marie.bank.model.Account;
import com.marie.bank.model.Client;
import com.marie.bank.model.NegativeAmountException;
import com.marie.bank.service.AccountService;
import com.marie.bank.service.ClientService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Account getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return true;
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public boolean depositOnAccount(@RequestParam Long id, @RequestParam double amount) {
        try {
            Account account = accountService.getAccount(id);
            account.deposit(amount);
            return true;
        } catch (NegativeAmountException ex) {
            return false;
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Account updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

}
