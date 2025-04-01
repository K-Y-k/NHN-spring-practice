package com.example.demo.bean;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderProcessor {
    final OrderReceiver orderReceiver;
    final PaymentProcessor paymentProcessor;

    public void process() {
        orderReceiver.receiveOrder();
        paymentProcessor.processPayment();
    }
}
