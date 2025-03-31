package org.example.config;

import org.example.student.Student;
import org.example.student.UniversityStudent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//TODO-2 UniversityStudent 를 Bean 으로 만든다 ** hint @Configuration, @Bean @**

/**
 * 방식1: Bean을 관리하는 Config 클래스에 @Configuration를 붙이고
 *       @Bean으로 사용할 객체를 등록하는 방식
 */
@Configuration
public class StudentConfig {
    @Bean
    public Student universityStudent(){
        return new UniversityStudent();
    }
}
