package org.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aop {


    //TODO-1 execution 으로 Student.identity() 적용
    @Pointcut("execution(* org.example.student.Student.identity())")
    public void studentCut() {
    }

    //TODO-2 within 으로 org.example.greeting 패키지 적용
    @Pointcut("within(org.example.greeting.*)")
    public void greetingCut() {
    }

    //TODO-3 커스텀 어노테이션 생성
    // @annotaion 을 이용하여 포인트 컷 생성. 원하는 메소드에 적용한다.
    @Pointcut("@annotation(org.example.annotation.CustomAnnotation)")
    public void annotationCut() {
    }

    @Before("greetingCut()")
    public void greeting(JoinPoint joinPoint) {
        System.out.println("greeting aop");
        System.out.println(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

    @Before("studentCut()")
    public void student(JoinPoint joinPoint) {
        System.out.println("student aop");
        System.out.println(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

    @Before("annotationCut()")
    public void annotation(JoinPoint joinPoint) {
        System.out.println("annotation aop");
        System.out.println(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }


}
