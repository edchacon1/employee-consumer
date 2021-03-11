package com.consumer.employee.exception;

/**
 * Custom exception to be more specific on employees issues.
 *
 * @author Dario Chacon
 * @version 1.0
 * @since 2021-03-10
 */
public class EmployeeConsumerException extends Exception {
    /**
     * Get Employee by id.
     *
     * @param -String- Exception message.
     */
    public EmployeeConsumerException(String message) {
        super(message);
    }
}

