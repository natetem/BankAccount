package com.marie.bank;

import com.marie.bank.model.Account;
import com.marie.bank.service.ClientService;
import com.marie.bank.model.Client;
import com.marie.bank.model.Operation;
import com.marie.bank.service.AccountService;
import com.marie.bank.service.OperationService;
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

    @Autowired
    private OperationService operationService;

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
        clientService.createClient(jack);
        clientService.createClient(chloe);
        clientService.createClient(kim);
        clientService.createClient(david);
        clientService.createClient(michelle);

        //create accounts
        Account accountJack1 = new Account(200, jack);
        Account accountJack2 = new Account(400, jack);
        Account accountChloe1 = new Account(300, chloe);
        accountService.createAccount(accountJack1);
        accountService.createAccount(accountJack2);
        accountService.createAccount(accountChloe1);
        //create operations
        Operation op1 = new Operation(200, accountJack1);
        Operation op2 = new Operation(400, accountJack1);
        operationService.depositOperation(op1);
        operationService.depositOperation(op2);
    }
}
