package com.nhnacademy.springbootmvc.controller;

import com.nhnacademy.springbootmvc.model.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * StringToRoleConverter를 추가한 덕분에 Role 객체로 파라미터를 받을 수 있게 됨
     */
    @GetMapping("/converter")
    public String converter(@RequestParam("role") Role role, Model model) {
        model.addAttribute("role", role);
        return "role";
    }

    /**
     * StringToBooleanConverter를 추가한 덕분에 boolean 타입 파라미터를 받을 수 있게 됨
     */
    //TODO 3: isStudent 를 request parameter 로 받아서 출력하도록 구현
    @GetMapping("/converter2")
    public String converter2(@RequestParam boolean isStudent, Model model) {
        model.addAttribute("isStudent", isStudent);
        return "student";
    }
}
