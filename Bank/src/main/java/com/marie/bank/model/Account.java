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
@RequiredArgsConstructor
public class Account {

    public final static AtomicInteger NUMBER_OF_ACCOUNTS = new AtomicInteger(1);
    private final int id;
    private double balance;
    private final Client client;
    private List<Operation> operations;

    public Account(Client client) {
        this.id = NUMBER_OF_ACCOUNTS.getAndIncrement();
        this.client = client;
        this.operations = new ArrayList();
    }

    public Account(Client client, double balance) {
        this(client);
        this.balance = balance;

    }

}
