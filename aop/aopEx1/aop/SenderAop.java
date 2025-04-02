package org.example.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Weaving
 * - 런타임 시점에서 Advice를 Target Object에 적용하는 과정을 의미한다.
 * - 프록시로 감싸서 AOP에 적용하는 것
 */

/**
 * @Aspect 로 스프링부트에게 알려줌
 */
// TODO-2 Aop 관련 설정을 하고, application context 에서 인식 할 수 있도록 한다. hint ** @Aspect, @Component **
@Aspect
@Component
public class SenderAop {

    /**
     * @Pointcut 로 스프링부트에게 어디에 AOP 를 적용할 건지 알려줌
     */
    //TODO-3 어디에 aop 를 적용할 건지 설정한다. hint ** @PointCut, execution **
    @Pointcut("execution(* org.example.sender..sendMessage(..))")
    public void cut() {
    }

    /**
     * @Around 로 어떤 작업을 할지 구현
     */
    // TODO-4 어떤 작업을 할지 결정한다. hint ** @Around, ProceedingJoinPoint, StopWatch(시간 측정) **
    @Around("cut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        /**
         * Advice : 취해질 조치 -> 시간 측정
         */
        StopWatch stopWatch = new StopWatch();
        System.out.println("===== StopWatch start =====");
        stopWatch.start();

        /**
         * 원본 객체(Target object)의 요청 수행
         */
        Object object = joinPoint.proceed();

        stopWatch.stop();
        System.out.println("===== StopWatch stop =====");
        System.out.println(stopWatch.prettyPrint());

        return object;
    }
}
