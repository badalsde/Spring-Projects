package com.movie.utility;

import com.movie.exception.MovieException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
@Component
public class ExceptionControllerAdvice {
    @Autowired
    private Environment environment;
    private static final Logger logger = LogManager.getLogger(ExceptionControllerAdvice.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> ExceptionHandler(Exception exception){
     ErrorInfo error = new ErrorInfo();
     error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
     error.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
     error.setTimestamp(LocalDateTime.now());
     return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MovieException.class)
    public ResponseEntity<ErrorInfo> MovieExceptionHandler(MovieException exception){
        ErrorInfo error = new ErrorInfo();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(environment.getProperty(exception.getMessage()));
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorInfo> ExceptionHandler2(Exception ex){
        logger.error(ex.getMessage(),ex);

        String errorMsg;
        if(ex instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) ex;
            errorMsg = manve.getBindingResult().getAllErrors()
                   .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                   .collect(Collectors.joining(", "));
        }else{
            ConstraintViolationException cve= (ConstraintViolationException) ex;
            errorMsg = cve.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
        }

        ErrorInfo error = new ErrorInfo();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(errorMsg);
        error.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
