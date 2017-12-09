package com.marie.bank;

import com.marie.bank.model.Account;
import com.marie.bank.service.ClientService;
import com.marie.bank.model.Client;
import com.marie.bank.service.AccountService;
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

        //create clients
        Client jack = new Client("jackb", "Jack", "Bauer", "123.");
        Client chloe = new Client("chloeo", "Chloe", "O'Brian", "123.");
        Client kim = new Client("kimb", "Kim", "Bauer", "123.");
        Client david = new Client("davidp", "David", "Palmer", "132.");
        Client michelle = new Client("michelled", "Michelle", "Dessler", "123.");
       
       

        //create accounts
        Account accountJack1 = new Account(200, jack);
        Account accountkim1 = new Account(400, kim);
        Account accountChloe1 = new Account(300, chloe);
        accountService.createAccount(accountJack1);
        accountService.createAccount(accountkim1);
        accountService.createAccount(accountChloe1);
        
        clientService.createClient(david);
        clientService.createClient(michelle);
        //create operations

        accountService.depositOperation(1, 200);
        accountService.depositOperation(2, 200);
    }
}
