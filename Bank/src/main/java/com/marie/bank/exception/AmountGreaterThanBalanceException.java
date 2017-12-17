package com.marie.bank.exception;

/**
 * This class manages the exception amount greater than balance
 *
 * @author <a href="mailto:mariejeanne.natete@gmail.com">Marie Jeanne NATETE</a>
 */
public class AmountGreaterThanBalanceException extends RuntimeException {

    public AmountGreaterThanBalanceException(String message) {
        super(message);
    }
}
