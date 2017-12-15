package com.marie.bank.service;

import com.marie.bank.exception.NotAllowedOperationException;
import com.marie.bank.model.Account;
import com.marie.bank.model.Client;
import com.marie.bank.model.Operation;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author marie
 */
public class AccountServiceTest {

    private Account account;
    private Account account2;
    private Operation operation;
    private List<Operation> operations;
    private final AccountService accountService = new AccountService();
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        createAccount();
        createOperataions();

    }

    private void createAccount() {
        Client client = new Client("Jack", "Bauer");
        account = new Account(client, 1000);
    }

    private void createOperataions() {
        operations = Arrays.asList(
                new Operation(LocalDate.of(2017, Month.FEBRUARY, 01), 300),
                new Operation(LocalDate.of(2017, Month.JANUARY, 01), 100),
                new Operation(LocalDate.of(2017, Month.JANUARY, 01), -100),
                new Operation(LocalDate.of(2017, Month.JUNE, 01), 1100),
                new Operation(LocalDate.of(2017, Month.MARCH, 01), 501),
                new Operation(LocalDate.of(2017, Month.JULY, 01), 11000)
        );
    }

    @Test
    public void should_return_oneThousandAndThreeHundred_when_balance_is_oneThousand_and_deposit_amount_is_threeHundred() {
        accountService.deposit(account, operations.get(0));
        assertThat(account.getBalance(), equalTo(1300.0));
    }

    @Test
    public void should_return_nineHundred_when_balance_is_oneThousand_and_windrawal_amount_is_oneHundred() {
        accountService.withdrawal(account, operations.get(1));
        assertThat(account.getBalance(), equalTo(900.0));
    }

    @Test
    public void should_throw_Exception_when_deposit_negative_amount() {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(startsWith("Amount must be positive"));
        accountService.deposit(account, operations.get(2));
    }

    @Test
    public void should_throw_Exception_when_withdrawal_negative_amount() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(startsWith("Amount must be positive"));
        accountService.deposit(account, operations.get(2));
    }

    @Test
    public void should_throw_Exception_when_withdrawal_amount_greater_than_balance() {
        thrown.expect(NotAllowedOperationException.class);
        thrown.expectMessage(startsWith("Amount can not be greater than"));
        accountService.withdrawal(account, operations.get(3));
    }

    @Test
    public void should_throw_Exception_when_deposit_amount_greater_than_authorised_amount() {
        thrown.expect(NotAllowedOperationException.class);
        thrown.expectMessage("Amount is greater than authorised amount on deposit");
        accountService.deposit(account, operations.get(5));
    }

    @Test
    public void should_throw_Exception_when_withdrawal_amount_greater_than_authorised_amount() {
        thrown.expect(NotAllowedOperationException.class);
        thrown.expectMessage("Amount is greater than authorised amount on withdrawal");
        accountService.withdrawal(account, operations.get(4));
    }

    @Test
    public void should_throw_Exception_when_deposit_on_null_account() {

        thrown.expect(NullPointerException.class);
        accountService.deposit(account2, operations.get(0));
    }

    @Test
    public void should_throw_Exception_when_withdrawal_on_null_account() {

        thrown.expect(NullPointerException.class);
        accountService.withdrawal(account2, operations.get(1));
    }

    @Test
    public void should_throw_Exception_when_deposit_a_null_operation() {

        thrown.expect(NullPointerException.class);
        accountService.deposit(account, operation);
    }

    @Test
    public void should_throw_Exception_when_withdarwal_a_null_operation() {

        thrown.expect(NullPointerException.class);
        accountService.withdrawal(account, operation);
    }

    @Test
    public void should_return_histotical_of_account() {

        PrintStream ps = new PrintStream(outContent);
        System.setOut(ps);
        accountService.deposit(account, operations.get(0));
        accountService.withdrawal(account, operations.get(1));
        accountService.historical(account);
        assertThat(outContent.toString(), containsString("Deposit"));
        PrintStream originalOut = System.out;
        System.setOut(originalOut);

    }

}
