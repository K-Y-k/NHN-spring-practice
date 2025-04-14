package com.nhnacademy.restcontrollerpractice.step4.controller;

import com.nhnacademy.restcontrollerpractice.step4.domain.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CsvHttpMessageConverterExController {

    @GetMapping(value = "/csvHttpMessageConverter", produces = "text/csv")
    public Member csvHttpMessageConverter() {
        return new Member("홍길동", 30);
    }

    /**
     * produces : 클라이언트에게 반환할 응답의 미디어 타입을 지정
     * consumes : 클라이언트가 보낼 요청의 미디어 타입을 지정
     */
    @PostMapping(value = "/csvHttpMessageConverter", produces = "application/json", consumes = "text/csv")
    public Member postMember(@RequestBody Member member) {
        // 받은 Member 객체 그대로 반환
        return member;
    }
}
