package org.example.student;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//TODO-4 UniversityStudent 가 InitializingBean, DisposableBean 를 구현한다.
public class UniversityStudent implements Student, InitializingBean, DisposableBean {

    @Override
    public void identity() {
        System.out.println("성인");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UniversityStudent 빈 생성");
    }
    @Override
    public void destroy() {
        System.out.println("UniversityStudent 빈 소멸");
    }
}
