package com.example.demo.bean;

import org.springframework.stereotype.Component;

// @Component로 빈 등록 방식
@Component
public class Chef {
    public void cook() {
        System.out.println("요리 한다");
    }
}
