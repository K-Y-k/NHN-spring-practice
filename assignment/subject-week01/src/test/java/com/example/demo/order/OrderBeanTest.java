package com.example.demo.order;

import com.example.demo.bean.OrderProcessor;
import com.example.demo.bean.OrderReceiver;
import com.example.demo.bean.PaymentProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

/**
 * 단위 테스트 방식 - Mock 활용
 */
@ExtendWith(MockitoExtension.class)
public class OrderBeanTest {
    
    // Mock 객체로 적용
    @Mock
    OrderReceiver orderReceiver;

    @Mock
    PaymentProcessor paymentProcessor;

    // 실행하는 클래스인 OrderProcessor 에 각 필드인 Mock 객체를 주입
    @InjectMocks
    OrderProcessor orderProcessor;


    @Test
    void orderProcessTest() {
        // given
        doNothing().when(orderReceiver).receiveOrder();
        doNothing().when(paymentProcessor).processPayment();

        // when
        orderProcessor.process();

        // then
        verify(orderReceiver,times(1)).receiveOrder();
        verify(paymentProcessor,times(1)).processPayment();
    }
}
