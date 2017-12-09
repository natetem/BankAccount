package com.marie.bank.model;

import com.marie.bank.exception.AmountGreaterThanBalanceException;
import com.marie.bank.exception.NegativeAmountException;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author marie
 */
public class AccountTest {

    private Account account;

    @Before
    public void setUp() {
        account = new Account(new Client("Jack", "Bauer"));
    }

    @Test
    public void depositPositiveAmountOnAccount() throws NegativeAmountException {
        account.deposit(100);
        assertThat(account.getBalance()).isEqualTo(100);
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
        account.deposit(200);
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
