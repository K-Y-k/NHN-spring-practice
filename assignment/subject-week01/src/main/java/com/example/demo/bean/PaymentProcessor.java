package com.example.demo.bean;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentProcessor {
    private PaymentProperties paymentProperties;

    public void processPayment() {
        System.out.println(paymentProperties.getMessage());
    }
}
