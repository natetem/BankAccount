package com.marie.bank.model;

import java.util.List;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * This class Manage the Accounts
 *
 * @author <a href="mailto:mariejeanne.natete@gmail.com">Marie Jeanne NATETE</a>
 */
@Data
@RequiredArgsConstructor
public class Account {
    private final int id;
    @NonNull 
    private double balance;
    private final  Client client;
    private List<Operation> operations;   
    
}
