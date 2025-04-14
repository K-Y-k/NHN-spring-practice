package com.nhnacademy.restcontrollerpractice.step5.config;

import com.nhnacademy.restcontrollerpractice.step5.resolver.PageableHandlerMethodArgumentResolver;
import com.nhnacademy.restcontrollerpractice.step5.resolver.RequesterHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREAN);  // 기본 Locale 설정
        return localeResolver;
    }

    /**
     * 직접 만든 ArgumentResolvers 구현체들 추가
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new RequesterHandlerMethodArgumentResolver(localeResolver()));
        resolvers.add(new PageableHandlerMethodArgumentResolver());
    }
}