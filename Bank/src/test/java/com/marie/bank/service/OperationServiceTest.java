
package com.marie.bank.service;

import com.marie.bank.exception.AmountGreaterThanBalanceException;
import com.marie.bank.exception.NegativeAmountException;
import static org.junit.Assert.assertTrue;
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

    @Test
    public void depositOnAccountOne() throws NegativeAmountException {
        boolean result = operationService.depositOperation(1, 200);
        assertTrue(result);
    }

    @Test
    public void withdrawalOnAccountOne() throws NegativeAmountException, AmountGreaterThanBalanceException {
        boolean result = operationService.withdrawalOperation(2, 10);
        assertTrue(result);

    }

}
