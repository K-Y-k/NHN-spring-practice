package org.example.config;

import org.example.greeting.AdvancedEnglishGreeting;
import org.example.greeting.EnglishGreeting;
import org.example.greeting.Greeting;
import org.example.greeting.KoreanGreeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreetingConfig {

    @Bean
    Greeting englishGreeting() {
        return new EnglishGreeting();
    }

    @Bean
    AdvancedEnglishGreeting advancedEnglishGreeting() {
        return new AdvancedEnglishGreeting();
    }

    @Bean
    KoreanGreeting koreanGreeting() {
        return new KoreanGreeting();
    }


}
