package com.marie.bank.exception;

/**
 * This class manages the exception not allowed operation
 *
 * @author <a href="mailto:mariejeanne.natete@gmail.com">Marie Jeanne NATETE</a>
 */
public class NotAllowedOperationException extends RuntimeException {

    public NotAllowedOperationException(String message) {
        super(message);
    }

}
