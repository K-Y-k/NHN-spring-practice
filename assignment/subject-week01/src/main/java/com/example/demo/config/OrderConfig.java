package com.example.demo.config;

import com.example.demo.bean.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration + @Bean으로 빈 등록 방식
// @ConfigurationProperties() 사용 조건2.@Configuration이 있는 클래스에 @EnableConfigurationProperties(OrderProcessor.class) 등록해줘야함
@Configuration
@EnableConfigurationProperties({OrderReceiveProperties.class, PaymentProperties.class})
public class OrderConfig {

    @Bean
    public OrderReceiver orderReceiver(OrderReceiveProperties orderReceiveProperties) {
        return new OrderReceiver(orderReceiveProperties);
    }

    @Bean
    public PaymentProcessor paymentProcessor(PaymentProperties paymentProperties) {
        return new PaymentProcessor(paymentProperties);
    }

    @Bean
    @ConditionalOnBean({PaymentProcessor.class, OrderReceiver.class})
    public OrderProcessor orderProcessor(OrderReceiver orderReceiver, PaymentProcessor paymentProcessor) {
        return new OrderProcessor(orderReceiver, paymentProcessor);
    }
}
