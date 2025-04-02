package org.example.config;

import org.example.student.HighSchoolStudent;
import org.example.student.UniversityStudent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    HighSchoolStudent highSchoolStudent() {
        return new HighSchoolStudent("석산고");
    }

    @Bean
    UniversityStudent universityStudent() {
        return new UniversityStudent("조선대");
    }


}
