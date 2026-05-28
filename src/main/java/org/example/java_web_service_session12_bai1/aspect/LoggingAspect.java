package org.example.java_web_service_session12_bai1.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* org.example.java_web_service_session12_bai1.controller.*.*(..))")
    public void logBeforeController(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();

        System.out.println("BEFORE");
        System.out.println("Method called: " + methodName);
        System.out.println("Arguments: " + Arrays.toString(args));
    }

    @AfterReturning(
            pointcut = "execution(* org.example.java_web_service_session12_bai1.service.*.*(..))",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        String methodName = joinPoint.getSignature().getName();

        System.out.println("AFTER RETURNING");
        System.out.println("Method: " + methodName);
        System.out.println("Returned: " + result);
    }

    @Around("execution(* org.example.java_web_service_session12_bai1.controller.*.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println("AROUND");
        System.out.println(
                joinPoint.getSignature().getName()
                        + " executed in "
                        + (end - start)
                        + " ms"
        );

        return result;
    }
}
