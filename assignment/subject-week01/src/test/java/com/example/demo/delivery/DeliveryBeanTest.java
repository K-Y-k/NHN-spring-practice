package com.example.demo.delivery;

import com.example.demo.bean.DeliveryService;
import com.example.demo.runner.AppStartupRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


/**
 * 단위 테스트 방식 - Spy 활용
 */
@ExtendWith(MockitoExtension.class)
public class DeliveryBeanTest {

    // Spy 객체로 적용
    @Spy
    DeliveryService deliveryService;

    // 실행하는 클래스인 AppStartupRunner 에 Spy 객체인 DeliveryService 주입
    @InjectMocks
    AppStartupRunner appStartupRunner;


    @Test
    public void deliveryServiceTest() throws Exception {
        // given : 실제 객체인 DeliveryService 에서 deliver() 메소드는 가짜로 설정
        doNothing().when(deliveryService).deliver();

        // when
        appStartupRunner.run(null);

        verify(deliveryService, times(1)).deliver();
    }
}
