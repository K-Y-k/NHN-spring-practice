package com.nhnacademy.springbootmvc.domain;

import lombok.Value;

@Value
public class UserRegisterRequest {
    String id;
    String password;
    int age;

}
