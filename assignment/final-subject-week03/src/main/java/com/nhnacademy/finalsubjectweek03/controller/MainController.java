package com.nhnacademy.finalsubjectweek03.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
public class MainController {
    @GetMapping("/")
    public String homeMain(@AuthenticationPrincipal UserDetails userDetails,
                           @AuthenticationPrincipal OAuth2User oAuth2User, Model model) {
        /// 일반 폼에서 로그인한 사용자인 경우
        if (Objects.nonNull(userDetails)) {
            model.addAttribute("loginName", userDetails.getUsername());
        }
        
        /// OAuth로 로그인한 사용자인 경우
        if (Objects.nonNull(oAuth2User)) {
            String loginName = oAuth2User.getAttribute("name");
            model.addAttribute("loginName", loginName);
        }

        return "main/home";
    }

    @GetMapping("/main/admin")
    public String adminMain() {
        return "main/admin";
    }

    @GetMapping("/main/member")
    public String memberMain() {
        return "main/member";
    }

    @GetMapping("/main/google")
    public String googleMain() {
        return "main/google";
    }
}
