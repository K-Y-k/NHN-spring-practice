package com.nhnacademy.finalsubjectweek03.auth;

import com.nhnacademy.finalsubjectweek03.service.FailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final RedisTemplate<String, Object> sessionRedisTemplate;
    private final FailCounter failCounter;
    private final FailService failService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("로그인 성공 : {}, {}", authentication.getName(), failCounter.getCounter(authentication.getName()));

        /// 로그인 성공 로직
        /// 카운터 초기화 작업을 수행하는 로직
        failCounter.reset(authentication.getName());
        failService.deleteFailedId(authentication.getName());


        /// 세션 값 설정 로직
        /// 1.기존 SESSIONID 쿠키 가져와서 기존 Redis의 세션값 삭제
        String oldSessionId = null;
        if (Objects.nonNull(request.getCookies())) {
            for (Cookie cookie : request.getCookies()) {
                if ("SESSIONID".equals(cookie.getName())) {
                    oldSessionId = cookie.getValue();
                    break;
                }
            }
        }
        if (Objects.nonNull(oldSessionId)) {
            sessionRedisTemplate.delete(oldSessionId);
            log.info("기존 세션 Redis에서 삭제: {}", oldSessionId);
        }

        /// 2.새로운 세션값을 쿠키와 Redis에 저장
        String sessionId = UUID.randomUUID().toString();

        // 쿠키에 세션 값 적용
        Cookie sessionCookie = new Cookie("SESSIONID", sessionId);
        sessionCookie.setHttpOnly(true);  // 보안 설정
        sessionCookie.setMaxAge(60 * 60); // 쿠키 유효시간 (1시간)
        sessionCookie.setPath("/");       // 모든 경로에서 쿠키 접근 가능
        response.addCookie(sessionCookie);

        // Redis에 세션값-아이디 쌍으로 저장
        sessionRedisTemplate.opsForValue().set(sessionId, authentication.getName());
        log.info("Redis에 세션 생성 {}, {}", sessionId, authentication.getName());

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
