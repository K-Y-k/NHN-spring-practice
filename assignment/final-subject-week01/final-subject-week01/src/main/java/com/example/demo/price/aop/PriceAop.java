package com.example.demo.price.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class PriceAop {

    @Pointcut("execution(* com.example.demo.shell.MyCommands.*(..))")
    public void priceCut() {}

    @Around("priceCut()")
    public Object priceCutAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("----- 선도형 class {}({}) --------->", joinPoint.getSignature().toString(), Arrays.toString(joinPoint.getArgs()));

        // 호출된 메소드 실행
        Object result = joinPoint.proceed();

        log.info("<----- 선도형 class {}({}) ----",  joinPoint.getSignature().toString(), result.toString());

        // 호출된 메소드 결과를 반환
        return result;
    }
}