package com.nhnacademy.finalsubjectweek03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.nhnacademy") /// @FeignClient 사용을 위해 선언
@SpringBootApplication
public class FinalSubjectWeek03Application {

    public static void main(String[] args) {
        SpringApplication.run(FinalSubjectWeek03Application.class, args);
    }

}
