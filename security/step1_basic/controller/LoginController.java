package com.nhnacademy.restcontrollerpractice.security.step1_basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final MemberService memberService;

    public LoginController(@Qualifier("memberService2") MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping()
    public String index() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(MemberLoginRequest loginRequest,
                               HttpServletRequest req, HttpServletResponse resp, Model model)  {
        Member memberResponse = memberService.login(loginRequest);
        model.addAttribute("loginName", memberResponse.getName());
        return "home";
    }
}
