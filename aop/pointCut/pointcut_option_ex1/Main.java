package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.example.aop", "org.example.config", "org.example.greeting", "org.example.runner"})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}