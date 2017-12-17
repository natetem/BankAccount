package com.marie.bank.exception;

/**
 * This class manages the exception negative amount
 *
 * @author <a href="mailto:mariejeanne.natete@gmail.com">Marie Jeanne NATETE</a>
 */
public class NegativeAmountException extends RuntimeException {

    public NegativeAmountException(String message) {
        super(message);
    }
}
