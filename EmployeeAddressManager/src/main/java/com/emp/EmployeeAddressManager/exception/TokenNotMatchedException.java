package com.emp.EmployeeAddressManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TokenNotMatchedException extends RuntimeException {

    public TokenNotMatchedException(String message)
    {
        super(message);
    }
}
