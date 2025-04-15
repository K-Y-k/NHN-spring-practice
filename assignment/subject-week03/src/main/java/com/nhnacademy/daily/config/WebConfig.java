package com.nhnacademy.daily.config;

import com.nhnacademy.daily.converter.MemberCsvHttpMessageConverter;
import com.nhnacademy.daily.converter.MemberDtoCsvHttpMessageConverter;
import com.nhnacademy.daily.converter.MemberPageCsvHttpMessageConverter;
import com.nhnacademy.daily.resolver.PageableHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 직접 만든 CsvHttpMessageConverter 구현체들 추가
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MemberCsvHttpMessageConverter());
        converters.add(new MemberDtoCsvHttpMessageConverter());
        converters.add(new MemberPageCsvHttpMessageConverter());
    }

    /**
     * 직접 만든 PageableHandlerMethodArgumentResolver 구현체 추가
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new PageableHandlerMethodArgumentResolver());
    }
}
