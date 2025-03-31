package org.example.student;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;


//TODO-2 @PostConstruct, @PreDestroy 를 사용한다.
@Component
public class HighSchoolStudent implements Student {

    @Override
    public void identity() {
        System.out.println("미성년자");
    }

    /**
     * 방식3 : @PostConstruct, @PreDestroy 활용하여 빈 생명주기 메소드 적용 방식
     */
    @PostConstruct
    public void customPostConstruct() {
        System.out.println("HighSchoolStudent - 빈 생성");
    }

    @PreDestroy
    public void customPreDestroy() {
        System.out.println("HighSchoolStudent - 빈 종료");
    }
}
