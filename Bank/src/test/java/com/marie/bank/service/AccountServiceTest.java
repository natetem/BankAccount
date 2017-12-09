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
        assertThat(account.getBalance()).isEqualTo(50);
        Client client = clientService.getClient(1);
        assertThat(account.getClient().getFirstName()).isEqualTo(client.getFirstName());
    }
    
    @Test
    public void findAllAccounts() {
        List<Account> accounts = accountService.findAll();
        assertThat(accounts.size()).isGreaterThan(2);
    }
    
    @Test
    public void createAccount() {
        Account expAccount = new Account(new Client("Marie", "Natete"));
        Account account = accountService.createAccount(expAccount);
        assertThat(account.getClient().getLastName()).isEqualTo(expAccount.getClient().getLastName());
    }
    
}
