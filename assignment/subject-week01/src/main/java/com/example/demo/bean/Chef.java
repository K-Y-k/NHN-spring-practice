package com.example.demo.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// @Component로 빈 등록 방식
@Component
public class Chef {
    @Value("${chef.message}")
    private String message;

    public void cook() {
        System.out.println(message);
    }
}
