package com.crud.tasks.audit.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {
    @Around("execution(* com.example.audit.service.*.*(..))")
    public Object measureTime(ProceedingJoinPoint pjp)throws Throwable{
        long start = System.currentTimeMillis();

        Object result = pjp.proceed();

        long end = System.currentTimeMillis();
        System.out.println("Execution time of " + pjp.getSignature() + ": " + (end - start) + "ms");

        return result;
    }
}
