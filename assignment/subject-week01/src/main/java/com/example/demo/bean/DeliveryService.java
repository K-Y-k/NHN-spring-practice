package com.example.demo.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DeliveryService {
    @Value("${delivery.message}")
    private String message;

    public void deliver() {
        System.out.println(message);
    }
}
