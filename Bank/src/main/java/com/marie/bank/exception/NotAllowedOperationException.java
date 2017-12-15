package com.marie.bank.exception;

/**
 *
 * @author marie
 */
public class NotAllowedOperationException extends RuntimeException {

    public NotAllowedOperationException(String message) {
        super(message);
    }

}
