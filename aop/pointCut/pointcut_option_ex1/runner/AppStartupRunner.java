package org.example.runner;

import lombok.RequiredArgsConstructor;
import org.example.greeting.AdvancedEnglishGreeting;
import org.example.greeting.Greeting;
import org.example.greeting.KoreanGreeting;
import org.example.greeting.SpecialHelloTarget;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AppStartupRunner implements ApplicationRunner {

    private final AdvancedEnglishGreeting advancedEnglishGreeting;
    private final Greeting englishGreeting;
    private final KoreanGreeting koreanGreeting;

    @Override
    public void run(ApplicationArguments args) {
        advancedEnglishGreeting.sayHello();

        System.out.println();

        advancedEnglishGreeting.saySpecialHello(new SpecialHelloTarget("sando", "seon"));

        System.out.println();

        englishGreeting.sayHello();

        System.out.println();

        koreanGreeting.sayHello();
    }
}