package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class ServiceAop {

    // Pointcut 지정 메소드 실행 전 시작
    @Before("receiveCut()")
    public void receiveOrder() {
        log.debug("--> OrderReceiverBean.receiveOrder()");
        log.debug("<-- OrderReceiverBean.receiveOrder()");
    }
    @Before("chefCut()")
    public void cook() {
        log.debug("--> Chef.cook()");
        log.debug("<-- Chef.cook()");
    }

    // Pointcut 지정 메소드 실행 후 시작
    @After("paymentCut()")
    public void payment() {
        log.debug("--> PaymentProcessorBean.processPayment()");
        log.debug("<-- PaymentProcessorBean.processPayment()");
    }
    @After("deliverCut()")
    public void deliver() {
        log.debug("--> DeliveryService.deliver()");
        log.debug("<-- DeliveryService.deliver()");
    }
    
    // Pointcut 지정 메소드 실행 전/후
    @Around("orderCut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("--> " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()+"()");

        Object proceed = joinPoint.proceed();

        log.debug("<-- " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()+"()");
        return proceed;
    }


    // bean 패키지 모두 적용법
    @Pointcut("execution(* com.example.demo.bean..*())")
    public void cut() {
    }
    // bean 패키지 제한법
    @Pointcut("within(com.example.demo.bean.*)")
    public void withInBean() {}

    // Chef 클래스의 cook() 메소드로 지정한 포인트컷
    @Pointcut("execution(* com.example.demo.bean.Chef.cook())")
    public void chefCut() {
    }

    // DeliveryService 클래스의 deliver() 메소드로 지정한 포인트컷
    @Pointcut("execution(* com.example.demo.bean.DeliveryService.deliver())")
    public void deliverCut() {
    }

    // OrderProcessor 클래스의 process() 메소드로 지정한 포인트컷
    @Pointcut("execution(* com.example.demo.bean.OrderProcessor.process())")
    public void orderCut() {
    }

    // OrderReceiver 클래스의 receiveOrder() 메소드로 지정한 포인트컷
    @Pointcut("execution(* com.example.demo.bean.OrderReceiver.receiveOrder())")
    public void receiveCut() {
    }

    // PaymentProcessor 클래스의 processPayment() 메소드로 지정한 포인트컷
    @Pointcut("execution(* com.example.demo.bean.PaymentProcessor.processPayment())")
    public void paymentCut() {
    }
}
