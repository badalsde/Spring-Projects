package com.fbs.utility;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fbs.exception.CardException;
import com.fbs.exception.FlightException;
import com.fbs.exception.PassengerException;
import com.fbs.exception.BookingException;
import com.fbs.exception.UserException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private Environment environment;

    // General Exception Handler (For internal server errors, other unhandled exceptions)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleGeneralException(Exception exception) {
        ErrorInfo error = new ErrorInfo();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE", "An unexpected error occurred."));
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // FlightException Handler (Custom flight exception handler)
    @ExceptionHandler(FlightException.class)
    public ResponseEntity<ErrorInfo> handleFlightException(FlightException exception) {
        ErrorInfo error = new ErrorInfo();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(environment.getProperty(exception.getMessage(), exception.getMessage()));
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    // UserException Handler (Custom user exception handler)
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorInfo> handleUserException(UserException exception) {
        ErrorInfo error = new ErrorInfo();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(environment.getProperty(exception.getMessage(), exception.getMessage()));
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // CardException Handler (Custom card exception handler)
    @ExceptionHandler(CardException.class)
    public ResponseEntity<ErrorInfo> handleCardException(CardException exception) {
        ErrorInfo error = new ErrorInfo();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(environment.getProperty(exception.getMessage(), exception.getMessage()));
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    // PassengerException Handler (Custom passenger exception handler)
    @ExceptionHandler(PassengerException.class)
    public ResponseEntity<ErrorInfo> handlePassengerException(PassengerException exception) {
        ErrorInfo error = new ErrorInfo();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(environment.getProperty(exception.getMessage(), exception.getMessage()));
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    // BookingException Handler (Custom ticket exception handler)
    @ExceptionHandler(BookingException.class)
    public ResponseEntity<ErrorInfo> handleTicketException(BookingException exception) {
        ErrorInfo error = new ErrorInfo();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(environment.getProperty(exception.getMessage(), exception.getMessage()));
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    // Validation Error Handler (For validation exceptions like missing fields or invalid format)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorInfo error = new ErrorInfo();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(exception.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", ")));
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // ConstraintViolationException Handler (Handles invalid URI parameters, path variables, or request parameters)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> handleConstraintViolationException(ConstraintViolationException exception) {
        ErrorInfo error = new ErrorInfo();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", ")));
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
