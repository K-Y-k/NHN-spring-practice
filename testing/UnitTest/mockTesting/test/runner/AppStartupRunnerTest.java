package org.example.runner;

import org.example.sender.Request;
import org.example.sender.Sender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Mock 어노테이션으로 생성 방식
 */
//TODO-1 Mock 사용을 위한 어노테이션 설정
@ExtendWith(MockitoExtension.class)
class AppStartupRunnerTest {

    /**
     * Mock 어노테이션으로 생성 방식
     */
    //TODO-2 Mock 으로 만들기 위한 어노테이션
    @Mock
    Sender sender; // Mock 객체 선언 (Mockito 어노테이션)
    //TODO-3 Mock 을 주입받기 위한 어노테이션
    @InjectMocks
    AppStartupRunner appStartupRunner;

    /**
     * Mockito API로 Mock 생성 방식
     */
    // Sender sender2 = Mockito.mock(Sender.class);


    @Test
    void run() throws IOException {
        /**
         * doNothing(): void 메서드를 호출해도 아무 일도 발생하지 않도록 설정
         */
        //TODO-4 Given: sendMessages 가 들어오면 아무 행동도 하지 않도록 설정한다. ** return 값이 void **
        doNothing().when(sender).sendMessage(any());

        // When
        appStartupRunner.run(null);

        /**
         * verify() : 특정 메서드가 몇 번 호출되었는지 확인 가능.
         *            times(1), never(), atLeastOnce(), atMost(2) 등 사용 가능.
         */
        //TODO-5 Then: sendMessages 가 1회 실행 된걸 확인한다.
        verify(this.sender, times(1)).sendMessage(any(Request.class));
    }
}