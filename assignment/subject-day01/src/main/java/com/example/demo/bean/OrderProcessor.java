package com.example.demo.bean;


public class OrderProcessor implements PaymentProcessor, OrderReceiver {

    @Override
    public void receiveOrder() {
        System.out.println("주문을 받는다");
    }

    @Override
    public void processPayment() {
        System.out.println("결제 처리를 한다");
    }
}
