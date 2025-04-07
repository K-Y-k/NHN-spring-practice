package com.nhnacademy.springmvcpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 공통된 경로는 클래스에 붙여 사용가능
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    /**
     * @RequestMapping으로 GET 조회 방식
     */
    // @RequestMapping(value = "/index", method = RequestMethod.GET)
    // public String index() {
    //     return "index";
    // }

    /**
     * @GetMapping으로 GET 조회 방식
     */
    @GetMapping("/index")
    public String index() {
        // View Resolver가 templates 폴더에 해당 파일명을 찾아줌
        return "index";
    }
}