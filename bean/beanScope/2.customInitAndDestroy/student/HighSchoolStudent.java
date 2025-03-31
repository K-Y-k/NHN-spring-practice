package org.example.student;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class HighSchoolStudent implements Student, InitializingBean, DisposableBean {

    @Override
    public void identity() {
        System.out.println("미성년자");
    }

    /**
     * 방식2 : InitializingBean, DisposableBean 인터페이스 활용 방식
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("HighSchoolStudent 빈 생성");
    }
    @Override
    public void destroy() throws Exception {
        System.out.println("HighSchoolStudent 빈 소멸");
    }
}
