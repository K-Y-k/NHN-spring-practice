package com.nhnacademy.subjectweek02.config;

import com.nhnacademy.subjectweek02.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    /**
     * 로그인 체크 인터셉터 선언
     */
    private final LoginInterceptor loginInterceptor;

    /**
     * LocaleResolver는
     * - 쿼리스트링인 ?locale이 있을 때 요청에 대한 로케일(locale)로 설정할 수 있다.
     * - 사용자가 특정 로케일을 선택하면 이를 세션에 저장해두고, 이후 요청에서 세션에 저장된 로케일을 사용하여 로케일을 결정할 수도 있다.
     * - 사용자의 Accept-Language HTTP 헤더를 기반으로 로케일을 결정할 수도 있다.
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

    /**
     * ResourceBundle 폴더에 관련된 외부 값이 저장된 properties 파일이 있으면 설정해서 빈으로 등록할 수 있다.
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("message");

        return messageSource;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 로케일(locale)을 변경하는 기능을 담당 인터셉터 추가
         */
        registry.addInterceptor(new LocaleChangeInterceptor());

        /**
         * 학생 등록, 로그인 요청을 제외한 모든 요청에 로그인 체크 인터셉터 추가
         */
       registry.addInterceptor(loginInterceptor)
               .addPathPatterns("/**")
               .excludePathPatterns("/login", "/student/register");
    }
}
