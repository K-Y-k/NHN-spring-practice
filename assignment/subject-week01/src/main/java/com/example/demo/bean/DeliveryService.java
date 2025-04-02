package com.example.demo.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeliveryService {
    @Value("${delivery.message}")
    private String message;

    public void deliver() {
        log.info(message);
    }
}
