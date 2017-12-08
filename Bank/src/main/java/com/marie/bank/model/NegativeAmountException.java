package com.marie.bank.model;

/**
 *
 * @author marie
 */
public class NegativeAmountException extends Exception {

    NegativeAmountException(String message) {
        super(message);
    }
}
