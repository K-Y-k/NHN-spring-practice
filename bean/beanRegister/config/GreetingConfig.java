package org.example.config;

import org.example.greeting.EnglishGreeting;
import org.example.greeting.Greeting;
import org.example.greeting.KoreanGreeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 방식1: Bean을 관리하는 Config 클래스에 @Configuration를 붙이고
 *       @Bean으로 사용할 객체를 등록하는 방식
 */
@Configuration
public class GreetingConfig {

    /**
     * 구현체가 달라도 부모 인터페이스로 반환한 Greeting이 2개가 되면
     * 어떤 구현체로 사용할지 프레임워크가 알지 못한다.
     * (동일 객체 2개 이상일 때는 @Primary로 우선순위 부여해야함)
     */
    // EnglishGreeting 구현체인 Greeting 등록
//    @Bean
//    Greeting englishGreeting() {
//        return new EnglishGreeting();
//    }

    // KoreanGreeting 구현체인 Greeting 등록
    //TODO-1 KoreanGreeting 을 Bean 으로 등록한다.
    @Bean
    Greeting koreanGreeting() {
        return new KoreanGreeting();
    }
}
