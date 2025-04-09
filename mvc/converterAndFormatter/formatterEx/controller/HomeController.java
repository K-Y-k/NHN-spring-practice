package com.nhnacademy.springbootmvc.controller;

import com.nhnacademy.springbootmvc.model.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class HomeController {

    @GetMapping("/formatter")
    public String formatter(@RequestParam("date") LocalDate date, Model model) {
        model.addAttribute("date", date);
        return "localdate";
    }

    //TODO 2: request parameter 로 money 를 받아서 출력하는 메서드 구현
    @GetMapping("/formatter2")
    public String money(@RequestParam BigDecimal money, Model model) {
        model.addAttribute("money", money);
        return "money";
    }
}
