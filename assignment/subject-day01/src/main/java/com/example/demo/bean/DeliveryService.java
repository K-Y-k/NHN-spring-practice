package com.example.demo.bean;

import org.springframework.stereotype.Component;

@Component
public class DeliveryService {
    public void  deliver() {
        System.out.println("배달 한다");
    }
}
