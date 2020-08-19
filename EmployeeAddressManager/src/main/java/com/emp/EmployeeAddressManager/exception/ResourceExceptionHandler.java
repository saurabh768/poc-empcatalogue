package com.emp.EmployeeAddressManager.exception;

import com.emp.EmployeeAddressManager.model.ErrorResponse;
import com.emp.EmployeeAddressManager.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * This exception is for UserNotFound
     *
     * @return error message and HTTP Status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException
    (ResourceNotFoundException ex, WebRequest request)
    {
        logger.info("Inside UserNotFoundException :: start");
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
        logger.info("Inside UserNotFoundException :: end");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * This exception is for AlreadyExistingUser
     *
     * @return error message and HTTP Status
     */
    @ExceptionHandler(ExistingUserException.class)
    public final ResponseEntity<ErrorResponse> handleExistingUserException
    (ExistingUserException ex,WebRequest request)
    {
        logger.info("Inside ExistingUserException :: start");
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
        logger.info("Inside ExistingUserException :: end");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * This exception is for AddressNotFound
     *
     * @return error message and HTTP Status
     */
    @ExceptionHandler(AddressNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleAddressNotFoundException
    (AddressNotFoundException ex, WebRequest request)
    {
        logger.info("Inside ExistingUserException :: start");
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
        logger.info("Inside ExistingUserException :: end");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * This exception is for TokenNotMatched
     *
     * @return error message and HTTP Status
     */
    @ExceptionHandler(TokenNotMatchedException.class)
    public final ResponseEntity<ErrorResponse> handleTokenNotMatchedException
    (TokenNotMatchedException ex, WebRequest request)
    {
        logger.info("Inside TokenNotMatchedException :: start");
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false));
        logger.info("Inside TokenNotMatchedException :: end");
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }


}
