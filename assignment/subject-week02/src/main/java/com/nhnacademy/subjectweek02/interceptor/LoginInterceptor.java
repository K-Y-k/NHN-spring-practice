package com.nhnacademy.subjectweek02.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import java.util.Objects;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 세션을 가져오고 검증하고 세션Id를 가져옴
        HttpSession session = request.getSession(false);
        String sessionId = null;
        if (Objects.nonNull(session)) {
            sessionId = (String) session.getAttribute("SESSION");
        }

        // 쿠키 전체를 가져와서 검증한 후 세션Id 쿠키를 가져옴
        Cookie[] cookies = request.getCookies();
        String cookieSessionId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("SESSION".equals(cookie.getName())) {
                    cookieSessionId = cookie.getValue();
                    break;
                }
            }
        }

        // 세션 ID가 없거나, 쿠키의 SESSION 값과 다르면 로그인 페이지로 리다이렉트
        if (StringUtils.isEmpty(sessionId) || !sessionId.equals(cookieSessionId)) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}