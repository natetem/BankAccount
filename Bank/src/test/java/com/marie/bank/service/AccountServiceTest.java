package com.marie.bank.service;

import com.marie.bank.model.Account;
import com.marie.bank.model.Client;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author marie
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @Test
    public void getAccountOne() {
        Account account = accountService.getAccount(1);
        Assert.assertNotNull(account);
        assertThat(account.getBalance()).isEqualTo(600);
        Client client = clientService.getClient(1);
        assertThat(account.getClient().getUsername()).isEqualTo(client.getUsername());
    }

    @Test
    public void findAllAccounts() {
        List<Account> accounts = accountService.findAll();
        assertThat(accounts.size()).isGreaterThan(3);
    }

    @Test
    public void findAccountsByClient() {
        Client client = clientService.getClient(1);
        List<Account> accounts = accountService.findAccountsByClient(client);
        assertThat(accounts.get(0).getClient().getFirstName()).isEqualTo("Jack");
    }

    @Test
    public void deleteAccountTwo() {
        accountService.deleteAccount(2);
        Account account = accountService.getAccount(2);
        Assert.assertNull(account);
        Client client = clientService.getClient(1);
        Assert.assertNotNull(client);
    }

    @Test
    public void createAccountFour() {
        Client client = clientService.getClient(2);
        Account expAccount = new Account(800, client);
        Account account = accountService.createAccount(expAccount);
        assertThat(account.getClient().getUsername()).isEqualTo(client.getUsername());
    }

    @Test
    public void updateAccountOne() {
        Client client = clientService.getClient(1);
        Account expAccount = new Account(1000, client);
        Account account = accountService.updateAccount(expAccount);
        assertThat(account.getBalance()).isEqualTo(expAccount.getBalance());
    }

    @Test
    public void depositOnAccountOne() {
        Account expAccount = accountService.getAccount(1);
        Account account = accountService.depositOperation(1, 200);
        assertThat(account.getBalance()).isGreaterThan(expAccount.getBalance());
    }

    @Test
    public void withdrawalOnAccountOne() {
        Account expAccount = accountService.getAccount(2);
        Account account = accountService.withdrawalOperation(2, 200);
        assertThat(account.getBalance()).isLessThan(expAccount.getBalance());

    }

}
