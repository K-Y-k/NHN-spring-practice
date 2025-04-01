package org.example.config;


import org.example.greeting.DefaultGreeting;
import org.example.greeting.EnglishGreeting;
import org.example.greeting.Greeting;
import org.example.greeting.KoreanGreeting;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreetingConfig {


    //TODO-7 defaultGreeting 의 위치를 여기로 옮기고 테스트 해본다.
    /**
     * 주의점: 위에서부터 읽으므로 이 기본 빈이 제일 위에 있을 경우 이 빈도 같이 읽어와 충돌이 발생할 수 있다.
     */
    //@Bean
    //@ConditionalOnMissingBean(Greeting.class)
    //Greeting defaultGreeting() {
    //    return new DefaultGreeting();
    //}

    //TODO-2 locale 의 값이 korean 이면 koreanGreeting 를 사용한다.
    @Bean
    @ConditionalOnProperty(value = "locale", havingValue = "korean")
    Greeting koreanGreeting() {
        return new KoreanGreeting();
    }

    //TODO-3 locale 의 값이 english 이고, American.class 가 존재하면 englishGreeting 를 사용한다.
    @Bean
    @ConditionalOnProperty(value = "locale", havingValue = "english") // 조건1 -> property인 locale 값이 english인 경우만
    @ConditionalOnClass(name = "org.example.greeting.American")       // 조건2 -> American 클래스가 존재하면(American 클래스를 삭제하면 조건 불일치됨)
    Greeting englishGreeting() {
        return new EnglishGreeting();
    }

    //TODO-1 @ConditionalOnMissingBean: Greeting 빈이 등록되지 않으면 defaultGreeting 를 사용한다.
    @Bean
    @ConditionalOnMissingBean(Greeting.class)
    Greeting defaultGreeting() {
        return new DefaultGreeting();
    }

}
