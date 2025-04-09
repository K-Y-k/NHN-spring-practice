package com.nhnacademy.subjectweek02.controller;

import com.nhnacademy.subjectweek02.domain.LoginRequest;
import com.nhnacademy.subjectweek02.domain.Student;
import com.nhnacademy.subjectweek02.repository.StudentRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Objects;

@Controller
public class StudentLoginController {
    private final StudentRepository studentRepository;

    public StudentLoginController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Model 방식
     */
    @GetMapping("/login")
    public String login(@CookieValue(value = "SESSION", required = false) String cookieSessionId,
                        HttpServletRequest req,
                        Model model) {
        HttpSession session = req.getSession(false);
        String sessionId = null;
        if (Objects.nonNull(session)) {
            sessionId = (String) session.getAttribute("SESSION");
        }

        if (Objects.nonNull(sessionId) && sessionId.equals(cookieSessionId)) {
            String studentId = (String) session.getAttribute("studentId");
            Student student = studentRepository.getStudent(studentId);
            model.addAttribute("student", Student.constructPasswordMaskedStudent(student));
            return "student/studentView";
        }

        return "login/loginForm";
    }

    /**
     * ModelMap 방식
     */
    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest,
                        HttpServletRequest req, HttpServletResponse res, ModelMap modelMap) {
        String id = loginRequest.getId();
        String password = loginRequest.getPassword();
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(password)) {
            return "redirect:/login";
        }

        if (studentRepository.match(id, password)) {
            // request.getSession()은 HttpSession을 가져오며
            // true 인자이면 기존 세션이 없을 때 새로 생성하고
            // false 인자이면 기존 세션이 없을 때 null을 반환한다.
            HttpSession session = req.getSession(true);

            // 쿠키로 세션Id를 추가
            Cookie sessionCookie = new Cookie("SESSION", session.getId());
            res.addCookie(sessionCookie);

            // 서버 세션에도 세션Id와 학생Id 추가
            session.setAttribute("SESSION", session.getId());
            session.setAttribute("studentId", id);

            Student student = studentRepository.getStudent(id);
            modelMap.put("student", Student.constructPasswordMaskedStudent(student));

            return "student/studentView";
        }

        return "redirect:/login";
    }
    
    /**
     * 로그아웃 추가
     */
    @GetMapping("/logout")
    public String login(HttpServletRequest req,  HttpServletResponse res) {
        // 세션 초기화
        HttpSession session = req.getSession(false);
        if (Objects.nonNull(session)) {
            session.invalidate();
        }

        // 쿠키를 찾고 만료 시간을 과거로 설정하여 삭제
        Cookie sessionCookie = new Cookie("SESSION", null);
        sessionCookie.setMaxAge(0);  // 쿠키 만료 시간을 0으로 설정
        sessionCookie.setPath("/");  // 쿠키가 설정된 경로와 동일한 경로로 설정

        // 만료된 쿠키를 클라이언트(브라우저)에 추가
        res.addCookie(sessionCookie);

        return "redirect:/login";
    }
}
