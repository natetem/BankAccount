package com.marie.bank.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * This class Manage the Bank Accounts
 *
 * @author <a href="mailto:mariejeanne.natete@gmail.com">Marie Jeanne NATETE</a>
 */
@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double balance;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonManagedReference
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Operation> operations;

    public Account() {

    }

    public Account(double balance, Client client) {
        this.balance = balance;
        this.client = client;
    }

    public void deposit(double amount)  {
//        if (amount < 0) {
//            throw new NegativeAmountException("Amount must be positive");
//        }
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

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
    

    @Override
    public String toString() {
        return String.format(
                "Account[id=%d, balance='%s', client='%s']",
                id, balance, client);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.balance) ^ (Double.doubleToLongBits(this.balance) >>> 32));
        hash = 71 * hash + Objects.hashCode(this.client);
        hash = 71 * hash + Objects.hashCode(this.operations);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (Double.doubleToLongBits(this.balance) != Double.doubleToLongBits(other.balance)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        return Objects.equals(this.operations, other.operations);
    }

 

   

}
