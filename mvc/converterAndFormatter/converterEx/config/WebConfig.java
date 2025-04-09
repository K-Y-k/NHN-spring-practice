package com.nhnacademy.springbootmvc.config;

import com.nhnacademy.springbootmvc.converter.StringToBooleanConverter;
import com.nhnacademy.springbootmvc.converter.StringToRoleConverter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        WebMvcConfigurer.super.addFormatters(registry);

        /**
         * 직접 구현한 컨버터 추가
         */
        registry.addConverter(new StringToRoleConverter());

        //TODO 2: StringToBoolean converter 등록
        registry.addConverter(new StringToBooleanConverter());
    }
}
