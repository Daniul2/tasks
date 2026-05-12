package com.crud.tasks.audit.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.audit.service.*.*(..))")
    public void logBefore(JoinPoint jp) {
        System.out.println("Before: " + jp.getSignature());
    }

    @After("execution(* com.example.audit.service.*.*(..))")
    public void logAfter(JoinPoint jp) {
        System.out.println("After: " + jp.getSignature());
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.audit.service.*.*(..))",
            throwing = "ex")
    public void logException(JoinPoint jp, Exception ex) {
        System.out.println("Exception in " + jp.getSignature() + ": " + ex.getMessage());
    }
}
