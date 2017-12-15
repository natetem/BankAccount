package com.marie.bank.service;

import com.marie.bank.enumeration.TypeOperation;
import com.marie.bank.exception.AmountGreaterThanBalanceException;
import com.marie.bank.exception.NegativeAmountException;
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
        account = new Account(client, 200);
    }

    private void createOperataions() {
        operations = Arrays.asList(
                new Operation(TypeOperation.Deposit, LocalDate.of(2017, Month.FEBRUARY, 01), 300),
                new Operation(TypeOperation.Deposit, LocalDate.of(2017, Month.DECEMBER, 01), -300),
                new Operation(TypeOperation.Withdrawal, LocalDate.of(2017, Month.JANUARY, 01), 100),
                new Operation(TypeOperation.Withdrawal, LocalDate.of(2017, Month.MARCH, 01), -100),
                new Operation(TypeOperation.Withdrawal, LocalDate.of(2017, Month.JUNE, 01), 300)
        );
    }

    @Test
    public void should_return_fiveHundred_when_balance_is_twoHundred_and_deposit_amount_is_threeHundred() throws NegativeAmountException {
        accountService.deposit(account, operations.get(0));
        assertThat(account.getBalance(), equalTo(500.0));
    }

    @Test
    public void should_return_oneHundred_when_balance_is_twoHundred_and_windrawal_amount_is_oneHundred() throws AmountGreaterThanBalanceException, NegativeAmountException {
        accountService.withdrawal(account, operations.get(2));
        assertThat(account.getBalance(), equalTo(100.0));
    }

    @Test
    public void should_throw_negativeAmountException_when_deposit_negative_amount() throws NegativeAmountException {

        thrown.expect(NegativeAmountException.class);
        thrown.expectMessage(startsWith("Amount must be positive"));
        accountService.deposit(account, operations.get(1));
    }

    @Test
    public void should_throw_negativeAmountException_when_withdrawal_negative_amount() throws NegativeAmountException {
        thrown.expect(NegativeAmountException.class);
        thrown.expectMessage(startsWith("Amount must be positive"));
        accountService.deposit(account, operations.get(3));
    }

    @Test
    public void should_throw_amountGreaterThanBalanceException_when_withdrawal_amount_greater_than_balance() throws AmountGreaterThanBalanceException, NegativeAmountException {
        thrown.expect(AmountGreaterThanBalanceException.class);
        thrown.expectMessage(startsWith("Amount can not be greater than"));
        accountService.withdrawal(account, operations.get(4));
    }

    @Test
    public void should_return_histotical_of_account() throws NegativeAmountException, AmountGreaterThanBalanceException {

        PrintStream ps = new PrintStream(outContent);
        System.setOut(ps);
        accountService.deposit(account, operations.get(0));
        accountService.withdrawal(account, operations.get(2));
        accountService.historical(account);
        assertThat( outContent.toString(),containsString("Deposit"));
        PrintStream originalOut = System.out;
        System.setOut(originalOut);

    }

}
