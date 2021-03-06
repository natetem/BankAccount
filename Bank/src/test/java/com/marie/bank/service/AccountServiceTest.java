package com.marie.bank.service;

import com.marie.bank.enumeration.TypeOperation;
import com.marie.bank.exception.AmountGreaterThanBalanceException;
import com.marie.bank.exception.NegativeAmountException;
import com.marie.bank.exception.NotAllowedOperationException;
import com.marie.bank.model.Account;
import com.marie.bank.model.Client;
import com.marie.bank.model.Operation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 * This class test the account service 
 *
 * @author <a href="mailto:mariejeanne.natete@gmail.com">Marie Jeanne NATETE</a>
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
        account = new Account(client, new BigDecimal(200));
    }

    @Test
    public void should_return_threeHundred_when_balance_is_twoHundred_and_deposit_amount_is_oneHundred() {
        //given 
        Operation operation = new Operation(LocalDate.of(2017, Month.FEBRUARY, 01), new BigDecimal(100));
        //when 
        Optional<BigDecimal> balance = accountService.deposit(account, operation);
        //then
        assertThat(account.getBalance(), equalTo(balance.get()));
        assertThat(account.getBalance(), equalTo(new BigDecimal(300)));

    }

    @Test
    public void should_return_oneHundred_when_balance_is_twoHundred_and_windrawal_amount_is_oneHundred() {
        //given
        Operation operation = new Operation(LocalDate.of(2017, Month.JANUARY, 01), new BigDecimal(100));
        //when
        Optional<BigDecimal> balance = accountService.withdrawal(account, operation);
        //then
        assertThat(account.getBalance(), equalTo(balance.get()));
        assertThat(account.getBalance(), equalTo(new BigDecimal(100)));
    }

    @Test
    public void should_return_empty_when_deposit_and_account_isnot_null_but_operation_is_null() {
        //given
        Operation operation = null;
        //when
        Optional<BigDecimal> balance = accountService.deposit(account, operation);
        //then
        assertThat(Optional.empty(), equalTo(balance));
    }
    
     @Test
    public void should_return_empty_when_withdrawal_and_account_isnot_null_but_operation_is_null() {
        //given
        Operation operation = null;
        //when
        Optional<BigDecimal> balance = accountService.withdrawal(account, operation);
        //then
        assertThat(Optional.empty(), equalTo(balance));
    }

    @Test(expected = NegativeAmountException.class)
    public void should_throw_Exception_when_deposit_negative_amount() {
        Operation operation = new Operation(LocalDate.of(2017, Month.JANUARY, 01), new BigDecimal(-100));
        accountService.deposit(account, operation);
    }

    @Test(expected = NegativeAmountException.class)
    public void should_throw_Exception_when_withdrawal_negative_amount() {
        Operation operation = new Operation(LocalDate.of(2017, Month.JANUARY, 01), new BigDecimal(-100));
        accountService.deposit(account, operation);
    }

    @Test(expected = AmountGreaterThanBalanceException.class)
    public void should_throw_Exception_when_withdrawal_amount_greater_than_balance() {
        Operation operation = new Operation(LocalDate.of(2017, Month.JANUARY, 01), new BigDecimal(300));
        accountService.withdrawal(account, operation);
    }

    @Test(expected = NotAllowedOperationException.class)
    public void should_throw_Exception_when_deposit_amount_greater_than_authorised_amount() {
        Operation operation = new Operation(LocalDate.of(2017, Month.JANUARY, 01), new BigDecimal(11000));
        accountService.deposit(account, operation);
    }

    @Test(expected = NotAllowedOperationException.class)
    public void should_throw_Exception_when_withdrawal_amount_greater_than_authorised_amount() {
        Operation operation = new Operation(LocalDate.of(2017, Month.JANUARY, 01), new BigDecimal(501));
        accountService.withdrawal(account, operation);
    }

    public void should_return_two_operations_when_histotical_of_account() {
        //given
        Operation operation1 = new Operation(LocalDate.of(2017, Month.JANUARY, 01), new BigDecimal(200));
        Operation operation2 = new Operation(LocalDate.of(2017, Month.JANUARY, 01), new BigDecimal(100));
        Optional<BigDecimal> balance1 = accountService.deposit(account, operation1);
        Optional<BigDecimal> balance2 = accountService.withdrawal(account, operation2);
        // when
        List<Operation> operations = accountService.historical(account);

        //then
        assertThat(operations.size(), equalTo(2));
        assertThat(operations.get(0).getBalance(), equalTo(balance1.get()));
        assertThat(operations.get(1).getBalance(), equalTo(balance2.get()));
        assertThat(operations.get(1).getType(), equalTo(TypeOperation.Withdrawal));
    }
    
      @Test
    public void should_return_empty_list_when_historical_and_account_is_null() {
        //given
        Account accountLocal = null;
        // when
        List<Operation> operations = accountService.historical(accountLocal);
        //then
        assertThat(operations, equalTo(Collections.EMPTY_LIST));
    }

}
