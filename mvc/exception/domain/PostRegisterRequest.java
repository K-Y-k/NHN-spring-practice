package com.nhnacademy.springbootmvc.domain;

import lombok.Value;

@Value
public class PostRegisterRequest {
    String title;
    String content;
}
