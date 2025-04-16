package com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
public class HomeController {
    /**
     * @AuthenticationPrincipal UserDetails userDetails 는 우리의 UserDetailsService에서 가공한 userDetail
     *
     *
     */
    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserDetails userDetails,
                        @AuthenticationPrincipal OAuth2User oAuth2User, Model model) {
        /**
         * 보통 OAuth로 인증을 넘기지만
         * 민감한 패스워드 같은 데이터를 제외한 회원 정보를 우리 서버 DB에 저장하긴 한다.
         */
        if (Objects.nonNull(oAuth2User)) {
            String loginName = oAuth2User.getAttribute("name");
            model.addAttribute("loginName", loginName);
        }

        if (Objects.nonNull(userDetails)) {
            model.addAttribute("loginName", userDetails.getUsername());
        }

        return "home";
    }
}
