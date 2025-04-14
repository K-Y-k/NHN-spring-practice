package com.nhnacademy.restcontrollerpractice.step1.controller;//package nhn.academy.step1;

import com.nhnacademy.restcontrollerpractice.step1.domain.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 실제 서버는 html 같은 것으로 반환해서 웹 브라우저로 렌더링 방식 보다는
 * 문자, JSON 형태의 데이터로 반환하는 것이 거의 대부분이다.
 *
 * 그래서 @ResponseBody에 @Controller를 붙여준 것
 */
@RestController
public class ConverterExController {
    /**
     * 문자로 반환 방법
     *  - StringHttpMessageConverter 구현체가 문자열 형태로 만들어준다.
     */
    @GetMapping("/stringHttpMessageConverter")
    public String me2() {
        Member member = new Member("kyk", 30);
        return member.toString();
    }

    /**
     * Member 객체로 반환 방법
     * - MappingJackson2httpMessageConverter 구현체가 JSON 형태로 만들어준다.
     */
    @GetMapping("/mappingJackson2httpMessageConverter")
    public Member me1() {
        return new Member("kyk", 30);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
