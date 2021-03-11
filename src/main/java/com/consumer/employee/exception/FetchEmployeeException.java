package com.consumer.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for fetching employees from external APIs.
 *
 * @author Dario Chacon
 * @version 1.0
 * @since 2021-03-10
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Employees not found.")
public class FetchEmployeeException extends RuntimeException{
    /**
     * Set message.
     *
     * @param message Exception message.
     */
    public FetchEmployeeException(String message) {
        super(message);
    }
}

