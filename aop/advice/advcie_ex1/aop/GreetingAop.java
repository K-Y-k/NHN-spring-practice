package org.example.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class GreetingAop {

    //TODO-1 실행 순서를 확인한다.
    // around 시작 -> before -> afterReturn(afterThrowing) -> after -> around 종료
    @Pointcut("execution(* org.example.greeting..sayHello())")
    public void cut() {
    }

    /**
     * @Before - Before Advice
     * - 조인포인트 전에 실행을 위한 선언
     */
    @Before("cut()")
    public void before() throws Throwable {
        System.out.println("before");
    }

    /**
     * @AfterReturning - AfterReturning Advice
     * 조인포인트(메소드 실행) 반환 후에 실행을 위한 선언
     */
    @AfterReturning("cut()")
    public void afterReturning() throws Throwable {
        System.out.println("afterReturning");
    }
    // Advice 내부에서 반환 값에 접근해야 하는 경우, returning 속성을 이용해서 advice 메소드 파라미터에 바인드 할 수 있다.
    @AfterReturning(
            pointcut="cut())",
            returning="retVal")
    public void afterReturning2(Object retVal) {
        System.out.println("afterReturning2 : " + retVal);
    }

    /**
     * @AfterThrowing - After Throwing Advice
     * - 조인포인트(메소드 실행)에서 Exception 이 발생한 후에 실행을 위한 선언
     */
    @AfterThrowing("cut()")
    public void afterThrowing() throws Throwable {
        System.out.println("afterThrowing");
    }
    // 원하는 타입의 Exception 이 발생할때만 매칭이 되고,
    // 발생한 Exception 에 접근하기를 원한다면 throwing 속성을 추가할 수 있다.
    @AfterThrowing(pointcut="cut()", throwing="ex")
    public void afterThrowing2(RuntimeException ex) {
        System.out.println("afterThrowing2 : " + ex);
    }

    /**
     * @After - After (Finally) Advice
     * - 조인포인트(메소드 실행) 에서 종료될때 실행을 위한 선언
     * - try-catch 구문의 finally 구문과 유사하기 때문에 메소드 실행중에 exception이 발생하더라도 실행한다.
     */
    @After("cut()")
    public void after() {
        System.out.println("after");
    }


    /**
     * @Around - Around Advice
     * - 메소드 실행의 전,후에 advice를 실행할 수 있는 기회를 제공한다.
     * - 대상 메소드가 실행하거나 하지 않도록 제어할 수도 있다. (proceed() 를 실행하지 않으면 됨)
     * - ProceedingJoinPoint 의 proceed() 를 호출하면 타겟메소드가 실행된다.
     * - 조건 : Object를 반환해야한다.
     *
     * Around만 사용성이 다른점
     * 1.Object를 리턴하고
     * 2.JoinPoint 매개객체로 받고 proceed() 실행하여 원본을 받음
     */
    @Around("cut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("around1");

        Object object = joinPoint.proceed();

        System.out.println("around2");

        return object;

    }
}
