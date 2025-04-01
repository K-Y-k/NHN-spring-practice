package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties("receive")
public class OrderReceiveProperties {
    private String message;
}
