package com.nhnacademy.finalsubjectweek03.config;

import com.nhnacademy.finalsubjectweek03.converter.MemberCsvHttpMessageConverter;
import com.nhnacademy.finalsubjectweek03.converter.MemberDtoCsvHttpMessageConverter;
import com.nhnacademy.finalsubjectweek03.resolver.PageableHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /// BCrypt 해시 알고리즘을 사용한 PasswordEncoder 구현체
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 직접 만든 CsvHttpMessageConverter 구현체들 추가
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MemberCsvHttpMessageConverter());
        converters.add(new MemberDtoCsvHttpMessageConverter());
    }

    /**
     * 직접 만든 PageableHandlerMethodArgumentResolver 구현체 추가
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new PageableHandlerMethodArgumentResolver());
    }

    /**
     * 컨트롤러 없이 뷰 반환 단순 매핑
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/403").setViewName("error/403"); // 에러 폼 추가
        registry.addViewController("/error/blocked").setViewName("error/blocked"); // 에러 폼 추가
    }
}
