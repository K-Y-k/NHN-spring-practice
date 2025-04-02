package org.example.config;

import org.example.greeting.EnglishGreeting;
import org.example.greeting.KoreanGreeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreetingConfig {

    @Bean
    public EnglishGreeting englishGreeting() {
        return new EnglishGreeting();
    }

    @Bean
    public KoreanGreeting koreanGreeting() {
        return new KoreanGreeting();
    }
}
