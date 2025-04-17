package com.nhnacademy.finalsubjectweek03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String homeMain() {
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
