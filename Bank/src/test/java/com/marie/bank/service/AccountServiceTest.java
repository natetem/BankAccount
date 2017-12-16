package com.marie.bank.service;

import com.marie.bank.enumeration.TypeOperation;
import com.marie.bank.exception.AmountGreaterThanBalanceException;
import com.marie.bank.exception.NegativeAmountException;
import com.marie.bank.exception.NotAllowedOperationException;
import com.marie.bank.model.Account;
import com.marie.bank.model.Client;
import com.marie.bank.model.Operation;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author marie
 */
public class AccountServiceTest {

    private Account account;
    private final AccountService accountService = new AccountServiceImpl();

    @Before
    public void setUp() {
        createAccount();
    }

    private void createAccount() {
        Client client = new Client("Jack", "Bauer");
        account = new Account(client, 200);
    }

    @Test
    public void should_return_threeHundred_when_balance_is_twoHundred_and_deposit_amount_is_oneHundred() {
        Operation operation = new Operation(LocalDate.of(2017, Month.FEBRUARY, 01), 100);
        double balance = accountService.deposit(account, operation);
        assertThat(account.getBalance(), equalTo(balance));
        assertThat(account.getBalance(), equalTo(300.0));

    }

    @Test
    public void should_return_oneHundred_when_balance_is_twoHundred_and_windrawal_amount_is_oneHundred() {
        Operation operation = new Operation(LocalDate.of(2017, Month.JANUARY, 01), 100);
        double balance = accountService.withdrawal(account, operation);
        assertThat(account.getBalance(), equalTo(balance));
        assertThat(account.getBalance(), equalTo(100.0));
    }

    @Test(expected = NegativeAmountException.class)
    public void should_throw_Exception_when_deposit_negative_amount() {
        Operation operation = new Operation(LocalDate.of(2017, Month.JANUARY, 01), -100);
        accountService.deposit(account, operation);
    }

    @Test(expected = NegativeAmountException.class)
    public void should_throw_Exception_when_withdrawal_negative_amount() {
        Operation operation = new Operation(LocalDate.of(2017, Month.JANUARY, 01), -100);
        accountService.deposit(account, operation);
    }

    @Test(expected = AmountGreaterThanBalanceException.class)
    public void should_throw_Exception_when_withdrawal_amount_greater_than_balance() {
        Operation operation = new Operation(LocalDate.of(2017, Month.JANUARY, 01), 300);
        accountService.withdrawal(account, operation);
    }

    @Test(expected = NotAllowedOperationException.class)
    public void should_throw_Exception_when_deposit_amount_greater_than_authorised_amount() {
        Operation operation = new Operation(LocalDate.of(2017, Month.JANUARY, 01), 11000);
        accountService.deposit(account, operation);
    }

    @Test(expected = NotAllowedOperationException.class)
    public void should_throw_Exception_when_withdrawal_amount_greater_than_authorised_amount() {
        Operation operation = new Operation(LocalDate.of(2017, Month.JANUARY, 01), 501);
        accountService.withdrawal(account, operation);
    }

    public void should_return_two_operations_when_histotical_account() {

        Operation operation1 = new Operation(LocalDate.of(2017, Month.JANUARY, 01), 200);
        Operation operation2 = new Operation(LocalDate.of(2017, Month.JANUARY, 01), 100);
        double balance1 = accountService.deposit(account, operation1);
        double balance2 = accountService.withdrawal(account, operation2);
        List<Operation> operations = accountService.historical(account);
        assertThat(operations.size(), equalTo(2));
        assertThat(operations.get(0).getBalance(), equalTo(balance1));
        assertThat(operations.get(1).getBalance(), equalTo(balance2));
        assertThat(operations.get(1).getType(), equalTo(TypeOperation.Withdrawal));
    }

}
