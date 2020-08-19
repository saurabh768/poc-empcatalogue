package com.emp.EmployeeAddressManager.exception;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(String message)
    {
        super(message);
    }
}

