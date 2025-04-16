package com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    FailCounter failCounter;

    private RedisTemplate<String, Object> sessionRedisTemplate;
    public CustomAuthenticationSuccessHandler( RedisTemplate<String, Object> sessionRedisTemplate) {
        this.sessionRedisTemplate = sessionRedisTemplate;

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        System.out.println("로그인 성공 : "  + authentication.getName() + ", " + failCounter.getCounter(authentication.getName()));

        // 로그인 성공 시 카운터 초기화 작업을 수행하는 로직
        failCounter.reset(authentication.getName());

        // redis에 세션을 저장하는 로직
        String sessionId = UUID.randomUUID().toString();

        Cookie sessionCookie = new Cookie("SESSIONID", sessionId);
        sessionCookie.setHttpOnly(true); // 보안 설정
        sessionCookie.setMaxAge(60 * 60); // 쿠키 유효시간 (1시간)
        sessionCookie.setPath("/"); // 모든 경로에서 쿠키 접근 가능
        response.addCookie(sessionCookie);

        AcademyUser academyUser = (AcademyUser)authentication.getPrincipal();
        sessionRedisTemplate.opsForValue().set(sessionId, academyUser.getUsername());

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
