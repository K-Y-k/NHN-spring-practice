package org.example.config;

import org.example.student.UniversityStudent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    //TODO-2 생성한 initMethod, destroyMethod 를 등록한다.
    /**
     * 방식1 : 해당 클래스에서의 메소드로 빈 생명주기 메소드로 적용 방식
     */
    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public UniversityStudent universityStudent() {
        return new UniversityStudent();
    }

}
