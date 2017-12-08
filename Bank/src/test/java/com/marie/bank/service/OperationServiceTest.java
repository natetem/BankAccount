package com.marie.bank.service;

import com.marie.bank.model.Account;
import com.marie.bank.model.Operation;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author marie
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationServiceTest {

    @Autowired
    private OperationService operationService;

    @Autowired
    private AccountService accountService;

    @Test
    public void getOperationOne() {
        Operation operation = operationService.getOperation(1L);
        Assert.assertNotNull(operation);
        assertThat(operation.getAmount()).isEqualTo(200.0);

    }

    @Test
    public void depositOnAccountOne() {
        Account account = accountService.getAccount(1L);
        Operation ExpOperation = new Operation(100, account);
        Operation operation = operationService.depositOperation(ExpOperation);
        Assert.assertEquals(ExpOperation.getAccount(), operation.getAccount());
        assertThat(ExpOperation.getAmount()).isEqualTo(operation.getAmount());
    }

}
