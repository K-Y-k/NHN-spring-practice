package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class PaymentProcessor {
    private PaymentProperties paymentProperties;

    public void processPayment() {
        log.info(paymentProperties.getMessage());
    }
}
