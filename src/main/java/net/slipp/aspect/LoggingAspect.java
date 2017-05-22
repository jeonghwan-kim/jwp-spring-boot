package net.slipp.aspect;

import net.slipp.web.UserController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by woowahan on 2017. 5. 22..
 */
@Component
@Aspect
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Pointcut("within(net.slipp..*)")
    public void logging() {

    }

    @Around("logging()")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        final Logger log =
                LoggerFactory.getLogger(pjp.getTarget().getClass());

        final String methodName = pjp.getSignature().getName();
        log.debug("[Logging Aspect] {}(): {}", methodName, pjp.getArgs());

        Object result = pjp.proceed();
        log.debug("[Logging Aspect] {}(): result={}", methodName, result);

        return result;
    }
}
