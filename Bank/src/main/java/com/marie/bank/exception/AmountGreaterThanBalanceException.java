package com.marie.bank.exception;

/**
 *
 * @author marie
 */
public class AmountGreaterThanBalanceException extends RuntimeException {

    public AmountGreaterThanBalanceException(String message) {
        super(message);
    }
}
