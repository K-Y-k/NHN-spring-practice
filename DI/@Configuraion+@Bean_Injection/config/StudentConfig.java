package org.example.config;

import org.example.student.HighSchoolStudent;
import org.example.student.Inner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    public Inner innerStudent() {
        return new Inner();
    }

    /**
     * highSchoolStudent(Inner inner)의 inner 파라미터는
     * innerStudent() 메서드가 반환한 Inner 객체로 자동 주입된다.
     */
    //TODO-2 주입 하고 싶은 객체를 파라미터로 넘긴 후, 초기화 할 때 추가한다.
    @Bean
    public HighSchoolStudent highSchoolStudent(Inner inner) {
        return new HighSchoolStudent(inner);
    }

}
