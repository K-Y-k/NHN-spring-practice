package com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    FailCounter failCounter;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");

        failCounter.increment(id);
        System.out.println("실패 카운트 : " + id + ", " + failCounter.getCounter(id));

        if (failCounter.getCounter(id) == 5) {
            System.out.println("5번 실패 발생!");
        }

        response.sendRedirect("/auth/login?error=true");
    }
}
