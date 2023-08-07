package com.example.spring_first_app.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;


@Aspect
@Service
@Slf4j
public class ServiceAspect {
    @Pointcut("within(com.example.spring_first_app.service.*)")
    public void pointCutBetweenServiceAndController() {
    }

    @After(value = "pointCutBetweenServiceAndController()")
    public void afterServiceCalled(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("Method " + signature.getMethod().getName() + " is called");
    }

}
