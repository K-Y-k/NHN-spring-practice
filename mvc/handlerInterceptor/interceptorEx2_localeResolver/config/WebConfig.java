package com.nhnacademy.springbootmvc.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // TODO #1: HandlerInterceptor을 `LocaleResolver`로 설정하기
    @Bean
    public LocaleResolver localeResolver() {
        /**
         * FixedLocaleResolver(Locale.KOREAN) 구현체를 사용하면 인자 값으로 고정된다.
         * 
         * 그러면 TODO 2에서 LocaleChangeInterceptor을 추가할 때 locale 값이 변경되지 않는다.
         * 그래서 다른 LocaleResolver 구현체로 사용
         * 
         * LocaleResolver는 
         * - 쿼리스트링인 ?locale이 있을 때 요청에 대한 locale을 설정할 수 있다.
         * - 사용자가 특정 로케일을 선택하면 이를 세션에 저장해두고, 이후 요청에서 세션에 저장된 로케일을 사용하여 로케일을 결정할 수도 있다.
         * - 사용자의 Accept-Language HTTP 헤더를 기반으로 로케일을 결정할 수도 있다.
         * 
         * FixedLocaleResolver(Locale.KOREAN)로 고정시키면
         * TODO 2에서 LocaleChangeInterceptor을 추가할 때 변경되지 않는다.
         * 그래서 다른 LocaleResolver로 사용
         */
        return new SessionLocaleResolver();
    }

    // TODO #4: `MessageSource` 빈 설정 - 다국어 지원.
    /**
     * ResourceBundle 폴더에 관련된 외부 값이 저장된 properties 파일이 있으면 가져와서 빈으로 등록할 수 있다.
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("message");
//        messageSource.setBasenames("message", "error");

        return messageSource;
    }

    // TODO #2: `LocaleChangeInterceptor` 추가
    //         `locale`이라는 파라미터로 전달된 값으로 locale을 변경.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
    }
}
