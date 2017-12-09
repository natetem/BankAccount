
package com.marie.bank.controller;

import com.marie.bank.exception.AmountGreaterThanBalanceException;
import com.marie.bank.exception.NegativeAmountException;
import com.marie.bank.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marie
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/operations")
@RestController
public class OperationController {

    @Autowired
    private OperationService operationService;

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public boolean depositOnAccount(@RequestParam int id, @RequestParam double amount) {
        try {
            return operationService.depositOperation(id, amount);
        } catch (NegativeAmountException ex) {
            return false;
        }
    }

    @RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
    public boolean withdrawalOnAccount(@RequestParam int id, @RequestParam double amount) {
        try {
            return operationService.withdrawalOperation(id, amount);
        } catch (NegativeAmountException | AmountGreaterThanBalanceException ex) {
            return false;
        }
    }

}
