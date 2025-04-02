package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class OrderReceiver {
    private OrderReceiveProperties orderReceiveProperties;

    public void receiveOrder() {
        log.info(orderReceiveProperties.getMessage());
    }
}
