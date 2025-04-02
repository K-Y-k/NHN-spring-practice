package com.example.demo.chef;

import com.example.demo.bean.*;
import com.example.demo.runner.AppStartupRunner;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.*;


/**
 * 통합 테스트 방식에서 Mock 적용
 */
@SpringBootTest
public class ChefBeanTest {

    // Mock 객체로 적용
    @MockitoBean
    Chef chef;

    // 실행하는 클래스인 AppStartupRunner 에 Mock 객체인 Chef 주입
    @InjectMocks
    AppStartupRunner appStartupRunner;


    @Test
    void chefTest() throws Exception {
        // given
        doNothing().when(chef).cook();

        // when
        appStartupRunner.run(null);

        // then
        verify(chef, times(1)).cook();
    }
}
