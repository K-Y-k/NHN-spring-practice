package com.nhnacademy.springbootmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Configuration으로 설정해주고 WebMvcConfigurer을 상속 받는다.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * WebMvcConfigurer을 오버라이드하여 커스텀할 수 있다.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);

        /**
         * /user2/register 경로가 들어오면 단순하게 registerUser 뷰 템플릿으로 보여주겠다는 뜻
         * 즉, 단순 요청인 경우 따로 컨트롤러 없이 이 방식으로 사용 가능
         */
        registry.addViewController("/user2/register").setViewName("registerUser");
        registry.addViewController("/post2/register").setViewName("registerPost");
    }
}
