package com.example.demo.config;

import com.example.demo.bean.OrderProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @CConfiguration + @Bean으로 빈 등록 방식
@Configuration
public class OrderConfig {

    @Bean
    public OrderProcessor orderProcessorBean() {
        return new OrderProcessor();
    }
}
