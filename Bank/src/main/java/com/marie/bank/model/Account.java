package com.marie.bank.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * This class Manage the Accounts
 *
 * @author <a href="mailto:mariejeanne.natete@gmail.com">Marie Jeanne NATETE</a>
 */
@Data
public class Account {

    private BigDecimal balance;
    private final Client client;
    private List<Operation> operations;

    public Account(Client client, BigDecimal balance) {
        this.client = client;
        this.operations = new ArrayList();
        this.balance = balance;

    }

}
