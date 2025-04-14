package com.nhnacademy.restcontrollerpractice.step1.config;//package nhn.academy.step1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 처음 실행할 때 MessageConverters 구현체들이 세팅됨
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // MappingJackson2HttpMessageConverter 구현체를 찾고 제거해주는 실습
        // 제거하게 되면 이제 객체로 반환할 때 JSON 형식의 문자열로 변환해서 반환해주지 못하여
        // 실제 창에서 띄워지지 못함

        /**
         * MappingJackson2HttpMessageConverter 구현체 인스턴스가 2개 있는데
         * - for문으로 조회하며 converters.remove()로 지우면 하나만 지워지는 이유
         *   : 하나가 지워지면서 index의 개수도 줄어들며 다음 것도 땡겨지므로
         *     for문이 다음까지 진행되지 못하고 종료 된 것
         */
//        for (HttpMessageConverter<?> converter : converters) {
//            if (converter instanceof MappingJackson2HttpMessageConverter) {
//                converters.remove(converter);
//            }
//        }

        /**
         * - converters.removeIf()로 하면 모두 지워지는 이유
         *   : removeIf() 안에 Iterator로 조회하며 삭제하는 기능이 있어 인덱스에 의존하지 않아 모두 지워진다.
         *
         * - 이렇게 모두 지우면 404 또는 406이 뜨는 이유
         *   : 이제 객체로 반환할 때 JSON 형식의 문자열로 변환해서 반환해주지 못하여 실제 창에서 띄워지지 못함
         */
        //converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        System.out.println(converters);
    }
}
