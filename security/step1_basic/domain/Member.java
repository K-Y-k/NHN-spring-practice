package com.nhnacademy.restcontrollerpractice.security.step1_basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private String id;
    private String password;
    private String name;
}
