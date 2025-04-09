package com.nhnacademy.springbootmvc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

// TODO #2: Java Config 파일 - @PropertySource 를 통해 properties 파일 가져오기
/**
 * some.properties 파일로 선언
 *
 * Spring Boot 환경에서는 @PropertySource 없이도 기본적으로 application.properties나 application.yml을 사용하여 프로퍼티를 주입받을 수 있지만,
 * some.properties와 같은 커스텀 프로퍼티 파일을 사용하려면 @PropertySource가 필요합니다.
 *
 * 일반 Spring 환경에서는 @PropertySource가 반드시 필요하며,
 * 이 어노테이션을 통해 해당 프로퍼티 파일을 명시적으로 로드해야 한다.
 *
 */
@Configuration
@PropertySource("classpath:some.properties")
public class PropertiesConfig {
    /**
     * 파일 내에 key1 속성값 주입
     */
    @Value("${key1}")
    private String key1;


    /**
     * 파일 내에 key2 속성값 주입
     */
    @Bean
    public List<String> keys(@Value("${key2}") String key2) {
        List<String> list = new ArrayList<>();
        list.add(key1);
        list.add(key2);

        return list;
    }
}