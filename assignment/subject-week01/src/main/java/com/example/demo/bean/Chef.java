package com.example.demo.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// @Component로 빈 등록 방식
@Slf4j
@Component
public class Chef {
    @Value("${chef.message}")
    private String message;

    public void cook() {
        log.info(message);
    }
}
