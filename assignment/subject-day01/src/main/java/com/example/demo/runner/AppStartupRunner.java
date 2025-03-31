package com.example.demo.runner;

import com.example.demo.bean.Chef;
import com.example.demo.bean.DeliveryService;
import com.example.demo.bean.OrderProcessor;
import com.example.demo.bean.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {
    // 1.생성자 주입 방식
    private final OrderProcessor orderProcessor;
    // 2.Setter 주입 방식
    private Chef chef;
    // 3.필드 주입 방식
    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    public void setChef(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        orderProcessor.receiveOrder();
        orderProcessor.processPayment();
        chef.cook();
        deliveryService.deliver();
    }
}
