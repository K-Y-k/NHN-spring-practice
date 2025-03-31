package org.example.runner;

import lombok.RequiredArgsConstructor;
import org.example.student.Student;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AppStartupRunner implements ApplicationRunner {


    private final ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) {

        System.out.println("=============");

        Student student = (Student) applicationContext.getBean("highSchoolStudent");
        student.identity();

        System.out.println("=============");

        student = (Student) applicationContext.getBean("universityStudent");
        student.identity();

        System.out.println("=============");


    }
}