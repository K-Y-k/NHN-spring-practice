package com.nhnacademy.finalsubjectweek02.common.interceptor;

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
        HttpSession session = request.getSession(false);
        String sessionId = null;
        if (Objects.nonNull(session)) {
            sessionId = (String) session.getAttribute("SESSION");
        }

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

        if (StringUtils.isEmpty(sessionId) || !sessionId.equals(cookieSessionId)) {
            response.sendRedirect("/cs/login");
            return false;
        }

        return true;
    }
}