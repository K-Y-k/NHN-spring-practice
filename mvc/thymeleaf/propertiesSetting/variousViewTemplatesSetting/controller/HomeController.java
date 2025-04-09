package com.nhnacademy.springbootmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * 반환할 때 같은 이름의 뷰 파일인 경우 폴더 경로로 구분하기 위해서
     * properties에서의 하위 경로 선언에 하위 폴더까지 지정해버리면 안된다.
     */
    @GetMapping("/")
    public String index() {
        // TODO #3 적절한 thymeleaf view 반환
        return "thymeleaf/index";
    }

    @GetMapping("/mustache")
    public String mustacheIndex(Model model) {
        model.addAttribute("message", "mustache Hello World!");
        // TODO #4 적절한 mustache view 반환
        return "mustache/index";
    }
}
