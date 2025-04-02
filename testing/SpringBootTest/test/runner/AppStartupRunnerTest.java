package org.example.runner;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * 통합테스트
 * - 빈을 등록하여 가져와서 사용하고 특정 부분만 Moking하여 테스트 방식
 * - 스프링부트에 로딩하고 실제 빈도 띄워야하므로 무겁다.
 */

/**
 * @Autowired로 주입한 방식 또는 @SpyBean 방식일 때는
 * 실행시점에 실제 객체를 빈을 주입 받아서 등록하기 때문에
 * ApplicationRunner를 상속받은 AppStartupRunner는 run()이 실행된다.
 * run()의 System.out.println(studentProperties.getFirstName()); 실행
 * 
 * @MockBean로 주입 받으면 가짜 객체이므로 실행되지 않음
 */
@SpringBootTest
class AppStartupRunnerTest {

    //TODO-1 AppStartupRunner 를 각각 @Autowired, @MockBean, @SpyBean 으로 수정한 뒤 테스트 해본다.
    @SpyBean
    private AppStartupRunner appStartupRunner;


    @Test
    public void test() {
        /**
         * @MockBean 방식 또는 @SpyBean 방식일 때 가짜 설정
         */
        //TODO-2 AppStartupRunner 의 returnRandom 을 모킹한다.
        when(appStartupRunner.returnRandom()).thenReturn(anyInt());

        /**
         * @SpyBean 방식일 때는 실제 객체이므로
         * run()의 System.out.println(studentProperties.getFirstName()); 한번 더 실행된다.
         */
        appStartupRunner.run(null);

        /**
         * @MockBean 방식 또는 @SpyBean 방식일 때는 가짜 설정됐으므로 0 출력
         */
        System.out.println(appStartupRunner.returnRandom());
    }

}