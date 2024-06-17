package com.wecare.utility;

import com.wecare.exception.WeCareException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.wecare.service.*Impl.*(..))", throwing = "exception")
    public void logServiceException(WeCareException exception) throws Exception {
        logger.error(exception.getMessage(),exception);
    }
}
