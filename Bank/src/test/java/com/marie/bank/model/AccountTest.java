package com.marie.bank.model;

import com.marie.bank.exception.AmountGreaterThanBalanceException;
import com.marie.bank.exception.NegativeAmountException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author marie
 */
public class AccountTest {

    private Client client;
    private Account account;

    @Before
    public void setUp() {
        client = new Client("jackb", "Jack", "Bauer", "123.");
        account = new Account(200, client);
    }

    @Test
    public void depositPositiveAmountOnAccount() throws NegativeAmountException {
        account.deposit(100);
        assertThat(account.getBalance()).isEqualTo(300);
    }

    @Test
    public void depositNegativeAmountOnAccount() {
        boolean thrown = false;
        try {
            account.deposit(-100);
        } catch (NegativeAmountException ex) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void withdrawalPositiveAmountOnAccount() throws NegativeAmountException, AmountGreaterThanBalanceException {
        account.withdrawal(100);
        assertThat(account.getBalance()).isEqualTo(100);
    }

    @Test
    public void withdrawalNegativeAmountOnAccount() throws AmountGreaterThanBalanceException {
        boolean thrown = false;
        try {
            account.withdrawal(-100);
        } catch (NegativeAmountException ex) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void withdrawalNoPermitAmountOnAccount() throws NegativeAmountException {
        boolean thrown = false;

        try {
            account.withdrawal(500);
        } catch (AmountGreaterThanBalanceException ex) {
            thrown = true;
        }

        assertTrue(thrown);
    }

}
