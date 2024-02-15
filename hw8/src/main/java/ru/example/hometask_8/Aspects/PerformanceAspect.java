package ru.example.hometask_8.Aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class PerformanceAspect {
    @Around("@annotation(ru.example.hometask_8.Annotations.Performance)")
    public Object logMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String timeStamp = new Date().toString();
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println(timeStamp + " Method " + joinPoint.getSignature().getName() + " complete for " + elapsedTime + " ms");
        
        return result;
    }
}
