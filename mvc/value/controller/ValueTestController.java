package com.nhnacademy.springbootmvc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ValueTestController {
    private final List<String> list;

    public ValueTestController(List<String> list) {
        this.list = list;
    }

    /**
     * PropertiesConfig에서 @PropertySource("classpath:some.properties")를 설정했기 때문에
     * some.properties의 key3 값을 컨트롤러에서도 가져올 수 있는 것이다.
     */
    @GetMapping("/value-test")
    @ResponseBody
    public String valueTest(@Value("${key3}") String key3,
                            @Value("12345") String key4,
                            @Value("#{systemProperties['java.home']}") String javaHome) {
        return String.join(",", list) + "," + key3 + "," + key4 + "\n"
                + javaHome;
    }
}
