package com.marie.bank.exception;

/**
 *
 * @author marie
 */
public class NegativeAmountException extends RuntimeException {

    public NegativeAmountException(String message) {
        super(message);
    }
}
