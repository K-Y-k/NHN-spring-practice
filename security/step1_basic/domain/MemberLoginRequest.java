package com.nhnacademy.restcontrollerpractice.security.step1_basic;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginRequest {
    private String id;
    private String password;
}
