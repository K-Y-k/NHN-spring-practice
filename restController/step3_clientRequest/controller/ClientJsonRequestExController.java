package com.nhnacademy.restcontrollerpractice.step3.controller;

import com.nhnacademy.restcontrollerpractice.step3.domain.ClassType;
import com.nhnacademy.restcontrollerpractice.step3.domain.Member;
import com.nhnacademy.restcontrollerpractice.step3.domain.MemberDto;
import com.nhnacademy.restcontrollerpractice.step3.domain.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClientJsonRequestExController {

    @GetMapping("/clientJsonRequest")
    public Member clientRequestMe() {
        return new Member("kyk", 30, Role.ADMIN, ClassType.A);
    }

    @PostMapping("/clientJsonRequest")
    public ResponseEntity<MemberDto> clientRequestMe2(@RequestBody MemberDto memberParam) {
        System.out.println(memberParam);
        return ResponseEntity.ok(memberParam);
    }
}
