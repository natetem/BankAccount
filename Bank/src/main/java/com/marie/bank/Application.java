package com.marie.bank;

import com.marie.bank.model.Account;
import com.marie.bank.service.ClientService;
import com.marie.bank.model.Client;
import com.marie.bank.service.AccountService;
import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        createData();
    }

    @Transactional
    private void createData() {
        Client jack = new Client("Jack", "Bauer");
        Client chloe = new Client("Chloe", "O'Brian");
        Client kim = new Client("Kim", "Bauer");
        Client david = new Client("David", "Palmer");
        Client michelle = new Client("Michelle", "Dessler");
        clientService.createClient(jack);
        clientService.createClient(chloe);
        clientService.createClient(kim);
        clientService.createClient(david);
        clientService.createClient(michelle);

        Account accountJack1 = new Account(200, jack);
        Account accountJack2 = new Account(400, jack);
        Account accountChloe1 = new Account(300, chloe);
        accountService.createAccount(accountJack1);
        accountService.createAccount(accountJack2);
        accountService.createAccount(accountChloe1);
    }
}
