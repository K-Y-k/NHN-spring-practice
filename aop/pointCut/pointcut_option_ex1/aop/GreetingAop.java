package org.example.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class GreetingAop {


    //TODO-1 예제의 구조를 먼저 살펴보기
    // AdvancedEnglishGreeting 는 EnglishGreeting 를 상속받고, SpecialGreeting 을 구현하는 구조.
    // EnglishGreeting 은 Greeting 을 구현.
    // KoreanGreeting 은 KoreanGreeting Bean 클래스로 빈을 만든 상태

    /**
     * '*' 와읻드 카드는 모든 경로를 뜻한다.
     */

    // TODO-2 모든 public 구현체. 실제로 실행해보면 오류가 뜬다.
    //  pulbic의 모든 메소드들을 해당시킨다.
    //  실행시키면 현재는 final 클래스가 포함되어 있어 실행에러
    //@Pointcut("execution(public * *(..))")

    // TODO-3 모든 getter 에 대해 적용. 실제로 실행해보면 오류가 뜬다.
    //@Pointcut("execution(* *get*(..))")

    // TODO-4 org.example 하위의 모든 메소드가 해당되어 실행
    //@Pointcut("execution(* org.example..*(..))")

    // TODO-5 org.example.greeting.Greeting 클래스(인터페이스) 의 모든 메소드
    //@Pointcut("execution(* org.example.greeting.Greeting.*(..))")

    // TODO-6 org.example.greeting.Greeting 클래스(인터페이스) 뿐만 아니라 상속 및 구현 하는 모든 메소드
    //@Pointcut("execution(* org.example.greeting.Greeting+.*(..))")

    // TODO-7 org.example.greeting 패키지의 클래스의 모든 메소드
    //@Pointcut("within(org.example.greeting.*)")

    // TODO-8 클래스로 제한 할 수도 있다. `+` 를 붙일 수 있다.
    //@Pointcut("within(org.example.greeting.AdvancedEnglishGreeting)")
    //@Pointcut("within(org.example.greeting.EnglishGreeting)")
    //@Pointcut("within(org.example.greeting.SpecialGreeting)")

    // TODO-10 this, target
    //  this: 주어진 타입을 구현한 스프링 AOP Proxy 객체에 매칭,
    //  target: 주어진 타입을 구현한 타겟 객체에 매칭
    //@Pointcut("this(org.example.greeting.SpecialGreeting) && withInGreeting()")
    //@Pointcut("target(org.example.greeting.SpecialGreeting) && withInGreeting()")

    // TODO-11 args
    //  args: 특정 파라미터를 받는 메소드들을 대상으로 할 수 있다.
    //@Pointcut("args(org.example.greeting.SpecialHelloTarget) && withInGreeting()")

    // TODO-12 @annotation
    //  @annotation: 특정 어노테이션이 가지고 있는 메소드를 지정. 인터페이스에 어노테이션이 지정되는 경우 작동하지 않음.
    //@Pointcut("@annotation(org.example.annotation.Method) && withInGreeting()")
    //@Pointcut("@annotation(org.example.annotation.MethodAtInterface) && withInGreeting()")


    // TODO-13 @within
    //  @within: 클래스 레벨에 적용된 어노테이션을 기준으로 조인 포인트를 매칭한다.
    //@Pointcut("@within(org.example.annotation.AtGreeting) && withInGreeting()")
    //@Pointcut("@within(org.example.annotation.AtEnglishGreeting) && withInGreeting()")
    //@Pointcut("@within(org.example.annotation.AtAdvancedEnglishGreeting) && withInGreeting()")

    // TODO-14 @target
    //  @target: 객체에 붙은 어노테이션을 기준으로 조인 포인트를 매칭한다.
    @Pointcut("@target(org.example.annotation.AtGreeting) && withInGreeting()")
    //@Pointcut("@target(org.example.annotation.AtEnglishGreeting) && withInGreeting()")
    //@Pointcut("@target(org.example.annotation.AtAdvancedEnglishGreeting) && withInGreeting()")

    // TODO-15 @args
    //  @args: 파라미터 인자의 어노테이션을 지준으로 조인포인트를 매칭한다.
    //@Pointcut("@args(org.example.annotation.ParameterArgument) && withInGreeting()")

    // TODO-16 bean
    //  bean: 빈 이름으로 조인포인트를 매칭한다.
    //@Pointcut("bean(advancedEnglishGreeting)")
    public void cut() {
    }


    // TODO-9 포인트 컷 범위 제한을 위한 포인트 컷
    @Pointcut("within(org.example.greeting.*)")
    public void withInGreeting() {
    }

    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("=== aop 대상 메소드 === : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }


}
