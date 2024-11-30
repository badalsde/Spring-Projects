package com.fbs.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.fbs.exception.FlightException;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.fbs.service.*(..))", throwing = "exception")
    public void logServiceException(FlightException exception) throws Exception {
        logger.error(exception.getMessage(),exception);
    }
}
