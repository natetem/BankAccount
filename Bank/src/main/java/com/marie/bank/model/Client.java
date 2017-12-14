package com.marie.bank.model;

import lombok.Data;

/**
 * This class Manage the clients
 *
 * @author <a href="mailto:mariejeanne.natete@gmail.com">Marie Jeanne NATETE</a>
 */
@Data
public class Client {
    private final int id;
    private final String firstName;
    private final String lastName;  
}
