package org.example;

import org.example.properties.NotStudentProperties;
import org.example.properties.StudentProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


/**
 * @EnableConfigurationProperties(StudentProperties.class)을 Main에 붙여준 이유는
 * SpringBootApplication에는 @Configuration이 있어서
 * 따로 @Configuration이 붙은 Config 클래스가 없으면 Main에 붙여준다.
 */
//TODO-2 @ConfigurationProperties 활성화
@SpringBootApplication
@EnableConfigurationProperties({StudentProperties.class, NotStudentProperties.class})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}