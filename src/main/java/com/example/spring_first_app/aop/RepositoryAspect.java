package com.example.spring_first_app.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Aspect
@Service
@Slf4j
public class RepositoryAspect {
    @Pointcut("execution(* com.example.spring_first_app.repository.StudentRepository.*(..)))")
    public void getAllDataFromRepository() {
    }

    @AfterReturning(value = "getAllDataFromRepository()", returning = "data")
    public void getDataObjectFromDatabase(JoinPoint joinPoint, Object data) {
        log.info("Data: " + data.toString());
    }
}
