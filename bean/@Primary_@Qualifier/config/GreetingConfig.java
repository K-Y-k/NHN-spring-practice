package org.example.config;

import org.example.greeting.EnglishGreeting;
import org.example.greeting.Greeting;
import org.example.greeting.JapaneseGreeting;
import org.example.greeting.KoreanGreeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

//TODO-1 englishGreeting, koreanGreeting, japaneseGreeting 3개가 선언 되어있고 @Primary 는 english 에 붙어있다.
// koreanGreeting 은 myCustomBeanName 로 이름이 등록했다.
@Configuration
public class GreetingConfig {

    /**
     * 동일한 객체가 있을 때 우선 사용할 객체로 설정한다.
     */
    @Primary
    @Bean
    Greeting englishGreeting() {
        return new EnglishGreeting();
    }

    @Bean
    Greeting koreanGreeting() {
        return new KoreanGreeting();
    }

    @Bean("myCustomBeanName")
    Greeting japaneseGreeting() {
        return new JapaneseGreeting();
    }


}
