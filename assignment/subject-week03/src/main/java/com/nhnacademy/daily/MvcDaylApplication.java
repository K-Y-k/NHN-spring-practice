package com.nhnacademy.daily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * FeignClient를 사용하기 위해 @EnableFeignClients 선언
 */
@EnableFeignClients
@SpringBootApplication
public class MvcDaylApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcDaylApplication.class, args);
	}

}
