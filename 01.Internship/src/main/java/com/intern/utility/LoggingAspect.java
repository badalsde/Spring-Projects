package com.intern.utility;

import com.intern.exception.InternException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.intern.service.*Impl.*(..))", throwing = "exception")
    public void logServiceException(InternException exception) throws Exception {
        logger.error(exception.getMessage(), exception);
    }
}
