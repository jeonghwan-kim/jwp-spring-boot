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
public class PerformanceAspect {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Pointcut("within(net.slipp..*)")
    public void performance() {

    }

    @Around("performance()")
    public Object doPerformance(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = pjp.proceed();
        long endTime = System.currentTimeMillis();
        log.debug("execution time: {}, {}", endTime - startTime, pjp.getSignature().getName());

        return returnValue;
    }
}
