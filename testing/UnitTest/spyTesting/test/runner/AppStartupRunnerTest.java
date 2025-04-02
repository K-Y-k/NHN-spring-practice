package org.example.runner;

import org.example.sender.DoorayWebHookSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Spy 어노테이션 방식
 */
//TODO-1 Spy 사용을 위한 어노테이션 설정
@ExtendWith(MockitoExtension.class)
class AppStartupRunnerTest {

    /**
     * Spy 어노테이션 방식
     */
    //TODO-2 Spy 으로 만들기 위한 어노테이션
    @Spy
    DoorayWebHookSender sender;
    //TODO-3 Spy 을 주입받기 위한 어노테이션
    @InjectMocks
    AppStartupRunner appStartupRunner;


    /**
     * Mockito API로 Spy 생성 방식
     */
    // DoorayWebHookSender sender2 = Mockito.spy(DoorayWebHookSender.class);


    /**
     * Spy
     * - 지정한 기능들만 Mocking 하고
     *   나머지는 기존 기능 그대로 사용하는 방법이다.
     * - 실제 메소드의 일부가 실행되기 때문에
     *   이로 인해 데이터베이스의 상태 변경, 파일 시스템의 변경, 네트워크 호출 등 실제 환경에 영향을 미칠 수 있는 작업이 발생할 수 있다.
     */

    @Test
    void run() throws IOException {
        /**
         * spy는 기존 메서드를 그대로 사용하므로
         * 가짜로 사용하고 설정하고 싶을 때만 mock 기능을 사용하는 것이다.
         */

        // 여기서는 sendMessage() 메서드를 mock으로 가짜 메소드를 실행하도록 선언
        //TODO-4 Given: sendMessages 가 들어오면 아무 행동도 하지 않도록 설정한다.
        doNothing().when(sender).sendMessage(any());

        // When
        appStartupRunner.run(null);

        //TODO-5 Then: sendMessages 가 1회 실행 된걸 확인한다.
        verify(sender, times(1)).sendMessage(any());
    }
}