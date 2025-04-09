package com.nhnacademy.springbootmvc.config;

import com.nhnacademy.springbootmvc.converter.StringToRoleConverter;
import com.nhnacademy.springbootmvc.formatter.LocalDateFormatter;
import com.nhnacademy.springbootmvc.formatter.MoneyFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Configuration 없어도 동작은 된다.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        WebMvcConfigurer.super.addFormatters(registry);

        /**
         * 직접 구현한 formatter 추가
         */
        registry.addFormatter(new LocalDateFormatter());

        //TODO 2: MoneyFormatter 등록
        registry.addFormatter(new MoneyFormatter());
    }
}
