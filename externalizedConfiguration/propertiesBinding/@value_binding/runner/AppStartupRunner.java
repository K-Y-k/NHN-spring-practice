package org.example.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {

    //TODO-2 @Value 어노테이션으로 사용
    @Value("${greeting.english}")
    private String english;

    @Value("${greeting.korean}")
    private String korean;

    @Override
    public void run(ApplicationArguments args) {
        System.out.println(english);
        System.out.println(korean);
    }
}