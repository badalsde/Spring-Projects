package com.railway.utility;

import com.railway.exception.RailwayException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);
    @AfterThrowing(pointcut = "execution(* com.railway.service.*Impl.*(..))", throwing = "exception")
    public void logServiceException(RailwayException exception) throws Exception {
        logger.error(exception.getMessage(), exception);
    }
}
