package org.example.runner;

import lombok.RequiredArgsConstructor;
import org.example.greeting.Greeting;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AppStartupRunner implements ApplicationRunner {

    private final Greeting greeting;

    @Override
    public void run(ApplicationArguments args) {
        try {
            greeting.sayHello();
        } catch (RuntimeException e) {
            System.out.println("catch exception: " + e.getMessage());
        }
    }
}