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
    public String login(@CookieValue(value = "SESSION", required = false) String sessionId,
                        @CookieValue(value = "ID", required = false) String id,
                        Model model) {
        if (!StringUtils.isEmpty(sessionId)) {
            Student student = studentRepository.getStudent(id);
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
            HttpSession session = req.getSession(true);

            Cookie cookie1 = new Cookie("SESSION", session.getId());
            Cookie cookie2 = new Cookie("ID", id);
            res.addCookie(cookie1);
            res.addCookie(cookie2);

            Student student = studentRepository.getStudent(id);
            modelMap.put("student", Student.constructPasswordMaskedStudent(student));

            return "student/studentView";
        }

        return "redirect:/login";
    }
}
