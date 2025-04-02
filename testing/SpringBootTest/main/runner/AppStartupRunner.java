package org.example.runner;

import lombok.RequiredArgsConstructor;
import org.example.properties.StudentProperties;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@RequiredArgsConstructor
@Component
public class AppStartupRunner implements ApplicationRunner {

    private final StudentProperties studentProperties;

    @Override
    public void run(ApplicationArguments args) {
        System.out.println(studentProperties.getFirstName());
    }

    public int returnRandom() {
        Random random = new Random();
        return random.nextInt(100); // 0부터 99 사이의 랜덤 숫자 반환
    }

}