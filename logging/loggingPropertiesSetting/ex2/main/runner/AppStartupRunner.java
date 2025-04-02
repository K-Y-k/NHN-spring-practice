package org.example.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.student.HighSchoolStudent;
import org.example.student.UniversityStudent;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
//TODO-1 logback 설정을 위한 코드가 필요하다 ** hint lombok 사용 여부에 따라 설정이 다르다.**
@Slf4j
public class AppStartupRunner implements ApplicationRunner {

    private final HighSchoolStudent highSchoolStudent;
    private final UniversityStudent universityStudent;

    @Override
    public void run(ApplicationArguments args) {

        //TODO-2 System.out.println 을 다른 방식으로 바꾼다.
        log.warn(highSchoolStudent.getSchool());
//        System.out.println(highSchoolStudent.getSchool());
//        System.out.println(highSchoolStudent.echo("hello, high school"));
//
//        System.out.println(universityStudent.getSchool());
//        System.out.println(universityStudent.echo("hello, university"));

    }
}