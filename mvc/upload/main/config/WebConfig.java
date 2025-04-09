package com.nhnacademy.springbootmvc.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * @Configuration이 없어도 잡아주긴 한다.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    // TODO #2: MultipartResolver 빈 설정
    @Bean
    public MultipartResolver multipartResolver() {
        /**
         * StandardServletMultipartResolver 구현체로 사용
         */
        return new StandardServletMultipartResolver();
    }

    // TODO #3: upload form 설정
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /**
         * /upload url일 때 upload 뷰로 띄워주는 단순 매핑 적용
         */
        registry.addViewController("/upload").setViewName("upload");
    }
}
