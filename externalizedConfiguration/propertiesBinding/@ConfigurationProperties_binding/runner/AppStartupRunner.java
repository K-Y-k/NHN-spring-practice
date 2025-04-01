package org.example.runner;

import lombok.RequiredArgsConstructor;
import org.example.properties.NotStudentProperties;
import org.example.properties.StudentProperties;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * StudentProperties, NotStudentProperties를 사용하려면 주입해줘야 한다.
 */
@Component
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {

    //TODO-4 Bean 처럼 사용
    final StudentProperties studentProperties;
    final NotStudentProperties notStudentProperties;

    @Override
    public void run(ApplicationArguments args) {
        System.out.println(studentProperties.getFirstName());
        System.out.println(notStudentProperties.getFirstName());
    }
}