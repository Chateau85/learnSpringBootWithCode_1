package hello.helloSpring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    private static final Logger log = LoggerFactory.getLogger(TimeTraceAop.class);

    @Around("execution(* hello.helloSpring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        log.debug("START: {}", joinPoint);
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            log.debug("END: {} {}ms", joinPoint, timeMs);
        }
    }
}
