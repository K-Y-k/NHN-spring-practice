package com.nhnacademy.springbootmvc.domain;

import lombok.Value;

@Value
public class PostModifyRequest {
    String title;
    String content;
}
