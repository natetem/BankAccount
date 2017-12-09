package com.marie.bank.exception;

/**
 *
 * @author marie
 */
public class AmountGreaterThanBalanceException extends Exception {

    public AmountGreaterThanBalanceException(String message) {
        super(message);
    }

}
