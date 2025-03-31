package org.example.student;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

//TODO-1 @PostConstruct, @PreDestroy 를 사용한다.
public class UniversityStudent implements Student {

    @Override
    public void identity() {
        System.out.println("성인");
    }

    @PostConstruct
    public void customPostConstruct() {
        System.out.println("UniversityStudent - 빈 생성");
    }
    
    @PreDestroy
    public void customPreDestroy() {
        System.out.println("UniversityStudent - 빈 종료");
    }
}
