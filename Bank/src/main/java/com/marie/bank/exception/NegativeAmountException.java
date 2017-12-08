package com.marie.bank.exception;

/**
 *
 * @author marie
 */
public class NegativeAmountException extends Exception {

    public NegativeAmountException(String message) {
        super(message);
    }
}
