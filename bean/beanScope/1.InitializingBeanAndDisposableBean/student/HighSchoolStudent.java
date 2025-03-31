package org.example.student;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//TODO-1 HighSchoolStudent 를 Bean 으로 만든다 ** hint @Component **
//TODO-3 HighSchoolStudent 가 InitializingBean, DisposableBean 를 구현한다.
/**
 * 방식2: @Component로 등록 방식
 */
/**
 * InitializingBean
 * DisposableBean
 */
@Component
public class HighSchoolStudent implements Student, InitializingBean, DisposableBean {

    @Override
    public void identity() {
        System.out.println("미성년자");
    }

    /**
     * InitializingBean의 afterPropertiesSet(): 빈이 생성 될 때 동작 메서드
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("HighSchoolStudent 빈 생성");
    }

    /**
     * DisposableBean의 destroy() : 빈이 소멸 될 때 동작 메서드
     */
    @Override
    public void destroy() {
        System.out.println("HighSchoolStudent 빈 소멸");
    }
}
