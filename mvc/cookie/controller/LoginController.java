package com.nhnacademy.springbootmvc.controller;

import com.nhnacademy.springbootmvc.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;


// TODO #1: Controller로 만드세요.
@Controller
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * 쿠키는 required의 기본 값이 true이다.
     */
    // TODO #2: `GET /login` 요청을 처리하세요.
    //          `SESSION` 이라는 쿠키가 있으면 로그인 완료 메세지 출력 (`loginSuccess.html`).
    //          `SESSION` 이라는 쿠키가 없으면 로그인 폼 화면 출력 (`loginForm.html`).
    @GetMapping("/login")
    public String login(@CookieValue(name = "SESSION", required = false) String sessionId,
                        Model model) {
        if (!StringUtils.isEmpty(sessionId)) {
            model.addAttribute("id", sessionId);
            return "loginSuccess";
        }

        return "loginForm";
    }

    // TODO #3: `POST /login` 요청을 처리하세요.
    //          `userRepository.matches(id, password)` 메서드 이용.
    //          로그인 성공 시 `SESSION` 쿠키에 session id 값 저장하고
    //          모델을 이용해서 `loginSuccess.html`에 세션 아이디 전달.
    //          로그인 실패 시 `/login`으로 redirect.
    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id, @RequestParam("pwd") String password,
                          HttpServletRequest req, HttpServletResponse res,
                          Model model) {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(password)) {
            return "redirect:/login";
        }

        if (userRepository.matches(id, password)) {
            // 서버 세션 생성
            HttpSession session = req.getSession(true);

            // 쿠키 SESSION에 서버에서 생성한 세션id 값을 넣음
            Cookie cookie = new Cookie("SESSION", session.getId());
            res.addCookie(cookie);

            model.addAttribute("id", session.getId());
            return "loginSuccess";
        }

        return "redirect:/login";
    }
}
