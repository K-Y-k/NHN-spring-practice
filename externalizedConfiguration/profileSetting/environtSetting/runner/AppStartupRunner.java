package org.example.runner;

import lombok.RequiredArgsConstructor;
import org.example.student.HighSchoolStudent;
import org.example.student.UniversityStudent;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class AppStartupRunner implements ApplicationRunner {

    private final HighSchoolStudent highSchoolStudent;
    private final UniversityStudent universityStudent;

    @Override
    public void run(ApplicationArguments args) {
        highSchoolStudent.identity();
        universityStudent.identity();
    }
}