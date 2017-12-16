package com.marie.bank.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * This class Manage the Accounts
 *
 * @author <a href="mailto:mariejeanne.natete@gmail.com">Marie Jeanne NATETE</a>
 */
@Data
public class Account {

    private double balance;
    private final Client client;
    private List<Operation> operations;

    public Account(Client client) {
        this.client = client;
        this.operations = new ArrayList();
    }

    public Account(Client client, double balance) {
        this(client);
        this.balance = balance;

    }

}
