package com.example.demo.account.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * AOP 클래스도 빈으로 등록해야 한다!
 */
@Slf4j
@Component
@Aspect
public class AccountAop {

    @Pointcut("execution(* com.example.demo.account.service.AuthenticationService.*(..))")
    public void authenticationServiceCut() {}

    @Before("authenticationServiceCut()")
    public void authenticationServiceCutBefore(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        if (args.length == 2) {
            log.info("{}([{}, {}])", name, args[0], args[1]);
        } else if (args.length == 1) {
            log.info("{}([{}])", name, args[0]);
        } else {
            log.info("{}([])", name);
        }
    }
}