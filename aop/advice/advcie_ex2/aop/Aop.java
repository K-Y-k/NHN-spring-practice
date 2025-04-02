package org.example.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.student.Student;
import org.example.student.UniversityStudent;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Aop {

    /**
     * 조건 1.within(org.example.student.*) -> student 하위에 있는 클래스에만 적용
     * 조건 2.argument를 String으로 적용
     */
    //TODO-1 student 에서 echo() 가 호출되는 상황에 대한 point cut 을 작성한다. hint ** args point cut 은 다른 포인트 컷으로 범위 제한을 해줘야 한다.**
    @Pointcut("within(org.example.student.*) && args(arg)")
    public void echoCut(String arg) {}

    /**
     * ..getSchool() -> 반환 값 상관없는 getSchool()을 사용할거라는 선언
     */
    //TODO-2 student 에서 getSchool() 이 호출되는 상황에 대한 point cut 을 작성한다.
    @Pointcut("execution(* org.example.student..getSchool())")
    public void schoolCut() {}

    //TODO-3 echo() 가 호출 될 때, 인자 값을 받아서 출력하는 aop 를 작성한다.
    @Before("echoCut(arg)")
    public void beforeCut(JoinPoint joinPoint, String arg) {
        System.out.println("from joinPoint: " + joinPoint.getArgs()[0].toString());
        System.out.println("args: " + arg);
    }

    //TODO-4 getSchool() 이 호출 될 때, UniversityStudent 인 경우 가로채고 다른 값을 반환하도록 수정한다.
    // 고려할 점
    // 1. 어떤 advice 를 사용해야 하는지
    // 2. 어떤 값을 보면 UniversityStudent 여부를 확인할 수 있을지?
    @Around("schoolCut()")
    public Object aroundSchoolCut(ProceedingJoinPoint joinPoint) throws Throwable {
        if (joinPoint.getTarget() instanceof UniversityStudent) {
            return "하버드";
        }

        return joinPoint.proceed();
    }


}
