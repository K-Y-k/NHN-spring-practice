package com.example.demo.bean;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderReceiver {
    private OrderReceiveProperties orderReceiveProperties;

    public void receiveOrder() {
        System.out.println(orderReceiveProperties.getMessage());
    }
}
