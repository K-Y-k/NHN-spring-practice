package com.nhnacademy.finalsubjectweek02.login.controller;

import com.nhnacademy.finalsubjectweek02.admin.service.AdminService;
import com.nhnacademy.finalsubjectweek02.login.dto.LoginRequest;
import com.nhnacademy.finalsubjectweek02.customer.service.CustomerService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs")
public class LoginController {
    private final AdminService adminService;
    private final CustomerService customerService;

    @GetMapping("/login")
    public String loginForm(@CookieValue(value = "SESSION", required = false) String cookieSessionId,
                            HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        String sessionId = null;
        if (Objects.nonNull(session)) {
            sessionId = (String) session.getAttribute("SESSION");
        }

        if (Objects.nonNull(sessionId) && sessionId.equals(cookieSessionId)) {
            String userId = (String) session.getAttribute("userId");

            if (adminService.existsAdmin(userId)) {
                return "redirect:/cs/admin/" + userId;
            } else {
                return "redirect:/cs/" + userId;
            }
        }

        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest,
                        HttpServletRequest req, HttpServletResponse res) {
        String userId = loginRequest.getId();
        String password = loginRequest.getPassword();
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(password)) {
            return "redirect:/cs/login";
        }

        if (adminService.match(userId, password) || customerService.match(userId, password)) {
            HttpSession session = req.getSession(true);

            Cookie sessionCookie = new Cookie("SESSION", session.getId());
            res.addCookie(sessionCookie);

            session.setAttribute("SESSION", session.getId());
            session.setAttribute("userId", userId);

            if (adminService.existsAdmin(userId)) {
                return "redirect:/cs/admin/" + userId;
            } else {
                return "redirect:/cs/" + userId;
            }
        }

        return "redirect:/cs/login";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest req,  HttpServletResponse res) {
        HttpSession session = req.getSession(false);
        if (Objects.nonNull(session)) {
            session.invalidate();
        }

        Cookie sessionCookie = new Cookie("SESSION", null);
        sessionCookie.setMaxAge(0);
        sessionCookie.setPath("/");

        res.addCookie(sessionCookie);

        return "redirect:/cs/login";
    }
}
