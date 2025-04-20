package com.nhnacademy.finalsubjectweek03.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final RedisTemplate<String, Object> sessionRedisTemplate;


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        /// 현재 세션 무효화 및 Redis에서 세션 삭제
        HttpSession session = request.getSession(false);
        if (session != null) {
            String sessionId = session.getId();
            session.invalidate();

            sessionRedisTemplate.delete(sessionId);
        }

        /// 쿠키 제거
        Cookie cookie = new Cookie("SESSIONID", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        log.info("로그아웃: 세션, 쿠키 제거 완료");
    }
}
