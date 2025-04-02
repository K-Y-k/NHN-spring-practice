package org.example.config;

import org.example.greeting.EnglishGreeting;
import org.example.greeting.KoreanGreeting;
import org.example.greeting.Greeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class GreetingConfig {

    @Primary
    @Bean
    Greeting englishGreeting() {
        return new EnglishGreeting();
    }

    @Bean
    Greeting koreanGreeting() {
        return new KoreanGreeting();
    }


}
