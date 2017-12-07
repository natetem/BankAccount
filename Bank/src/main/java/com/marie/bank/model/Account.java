package com.marie.bank.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author marie
 */
@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double balance;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Account() {

    }

    public Account(double balance, Client client) {
        this.balance = balance;
        this.client = client;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public Long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public Client getClient() {
        return client;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setClient(Client client) {
        this.client = client;
    }
      @Override
    public String toString() {
        return String.format(
                "Account[id=%d, balance='%s', client='%s']",
                id, balance, client);
    }
    

}
