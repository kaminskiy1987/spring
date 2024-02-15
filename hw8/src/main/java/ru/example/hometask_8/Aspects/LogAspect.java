package ru.example.hometask_8.Aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Stream;

@Component
@Aspect
public class LogAspect {
    @Around("@annotation(ru.example.hometask_8.Annotations.Log)")
    public Object logMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String timeStamp = new Date().toString();
        System.out.println(timeStamp + " Method " + joinPoint.getSignature().getName() + " started");
        System.out.println(timeStamp + "    params: ");
        Stream.of(joinPoint.getArgs()).forEach(arg -> System.out.println(timeStamp + "      " + arg.toString()));

        Object result = joinPoint.proceed();

        System.out.println(timeStamp + " Method " + joinPoint.getSignature().getName() + " successfully complete");
        System.out.println(timeStamp + "    result: ");
        System.out.println(timeStamp + "        " + result.toString());
        return result;
    }
}
