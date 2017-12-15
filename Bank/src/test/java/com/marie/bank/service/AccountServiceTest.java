package com.marie.bank.service;

import com.marie.bank.enumeration.TypeOperation;
import com.marie.bank.exception.AmountGreaterThanBalanceException;
import com.marie.bank.exception.NegativeAmountException;
import com.marie.bank.model.Account;
import com.marie.bank.model.Client;
import com.marie.bank.model.Operation;
import java.time.LocalDate;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.is;
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
    private Operation operationDeposit1;
    private Operation operationDeposit2;
    private Operation operationWithdrawal1;
    private Operation operationWithdrawal2;
    private Operation operationWithdrawal3;
    private AccountService accountService;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        Client client = new Client(1, "Jack", "Bauer");
        account = new Account(1, 200, client);
        accountService = new AccountService();
        operationDeposit1 = new Operation(TypeOperation.Deposit, LocalDate.now(), 300);
        operationDeposit2 = new Operation(TypeOperation.Deposit, LocalDate.now(), -300);
        operationWithdrawal1 = new Operation(TypeOperation.Withdrawal, LocalDate.now(), 100);
        operationWithdrawal2 = new Operation(TypeOperation.Withdrawal, LocalDate.now(), -100);
        operationWithdrawal3 = new Operation(TypeOperation.Withdrawal, LocalDate.now(), 300);
    }

    @Test
    public void should_return_fiveHundred_when_balance_is_twoHundred_and_deposit_amount_is_threeHundred() throws NegativeAmountException {
        accountService.deposit(account, operationDeposit1);
        assertThat(account.getBalance(), is(500.0));
    }

    @Test
    public void should_return_oneHundred_when_balance_is_twoHundred_and_windrawal_amount_is_oneHundred() throws AmountGreaterThanBalanceException, NegativeAmountException {
        accountService.withdrawal(account, operationWithdrawal1);
        assertThat(account.getBalance(), is(100.0));
    }

    @Test
    public void should_throw_negativeAmountException_when_deposit_negative_amount() throws NegativeAmountException {

        thrown.expect(NegativeAmountException.class);
        thrown.expectMessage(startsWith("Amount must be positive"));
        accountService.deposit(account, operationDeposit2);
    }

    @Test
    public void should_throw_negativeAmountException_when_withdrawal_negative_amount() throws NegativeAmountException {
        thrown.expect(NegativeAmountException.class);
        thrown.expectMessage(startsWith("Amount must be positive"));
        accountService.deposit(account, operationWithdrawal2);
    }

    @Test
    public void should_throw_amountGreaterThanBalanceException_when_withdrawal_amount_greater_than_balance() throws AmountGreaterThanBalanceException, NegativeAmountException {
        thrown.expect(AmountGreaterThanBalanceException.class);
        thrown.expectMessage(startsWith("Amount can not be greater than"));
        accountService.withdrawal(account, operationWithdrawal3);
    }

    @Test
    public void should_return_the() throws NegativeAmountException {
        accountService.deposit(account, operationDeposit1);
        String s = accountService.historical(account);
        assertThat(s, CoreMatchers.containsString("Deposit"));

    }

}
