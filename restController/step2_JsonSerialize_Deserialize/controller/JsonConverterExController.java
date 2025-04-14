package com.nhnacademy.restcontrollerpractice.step2.controller;

import com.nhnacademy.restcontrollerpractice.step2.domain.ClassType;
import com.nhnacademy.restcontrollerpractice.step2.domain.Member;
import com.nhnacademy.restcontrollerpractice.step2.domain.MemberDto;
import com.nhnacademy.restcontrollerpractice.step2.domain.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JsonConverterExController {
    
    /**
     * 객체로 반환 방법
     * - MappingJackson2httpMessageConverter 구현체가 JSON 형태로 만들어준다.
     */
    @GetMapping("/json")
    public Member json1() {
        return new Member("aa", 10, Role.ADMIN, ClassType.A);
    }

    /**
     * ResponseEntity<T>는 HTTP 응답 전체를 표현할 수 있는 Spring 클래스
     * 즉, 응답의
     * - 상태 코드 (200, 404, 500 등)
     * - 헤더
     * - 바디 (Body)
     */
    @PostMapping("/json")
    public ResponseEntity<MemberDto> json2(@RequestBody MemberDto memberParam) {
        System.out.println(memberParam);
        return ResponseEntity.ok(memberParam);
    }
}
