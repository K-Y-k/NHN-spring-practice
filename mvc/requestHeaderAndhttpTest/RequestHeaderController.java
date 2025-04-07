package com.nhnacademy.springbootmvc.controller;

import com.nhnacademy.springbootmvc.domain.User;
import com.nhnacademy.springbootmvc.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestHeaderController {

    private final UserRepository userRepository;

    public RequestHeaderController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/requestHeader")
    public String index(@RequestHeader("User-Agent") String userAgent,
                        @RequestParam String id,
                        Model model) {
        User user = userRepository.getUser(id);
        model.addAttribute("user", user);

        if (userAgent.contains("Android")) {
            System.out.println("User-Agent: android");
            model.addAttribute("device", userAgent);
        } else if (userAgent.contains("iPhone")) {
            System.out.println("User-Agent: ios");
            model.addAttribute("device", userAgent);
        } else {
            System.out.println("User-Agent: PC");
            model.addAttribute("device", userAgent);
        }

        return "user";
    }
}
