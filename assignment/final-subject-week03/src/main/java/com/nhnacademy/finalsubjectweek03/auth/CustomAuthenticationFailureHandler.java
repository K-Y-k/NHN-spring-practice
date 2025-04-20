package com.nhnacademy.finalsubjectweek03.auth;

import com.nhnacademy.finalsubjectweek03.messenger.DoorayMessengerRequest;
import com.nhnacademy.finalsubjectweek03.messenger.MessengerClient;
import com.nhnacademy.finalsubjectweek03.service.FailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final MessengerClient messengerClient;
    private final FailCounter failCounter;
    private final FailService failService;


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String id = request.getParameter("id");

        if (failService.existsFailedId(id)) {
            String errorMessage = "입력한 아이디는 일정 시간 동안 차단되어 있습니다. 남은 시간: " + failService.getTTLToSeconds(id) + "초";

            // 로그인 페이지로 리디렉션하면서 에러 메시지를 쿼리 파라미터로 전달
            response.sendRedirect("/error/blocked?message=" + URLEncoder.encode(errorMessage, "UTF-8"));
            return;
        }


        failCounter.increment(id);
        log.info("실패 카운트 : {}, {}", id, failCounter.getCounter(id));

        if (failCounter.getCounter(id) >= 5) {
            log.info("5번이상 실패 발생! 해당 id 로그인 차단");
            failService.saveFailedId(id);

            DoorayMessengerRequest messengerRequest = new DoorayMessengerRequest("알림봇",  id + ": 해당 아이디는 로그인 5회 실패로 차단되었습니다. 차단 해제까지 남은 시간 " + failService.getTTLToSeconds(id) + "초");
            messengerClient.sendNotification(messengerRequest);

            String errorMessage = "입력한 아이디는 일정 시간 동안 차단되어 있습니다. 남은 시간: " + failService.getTTLToSeconds(id) + "초";
            response.sendRedirect("/error/blocked?message=" + URLEncoder.encode(errorMessage, "UTF-8"));
            return;
        }

        response.sendRedirect("/auth/login?error=true");
    }
}
