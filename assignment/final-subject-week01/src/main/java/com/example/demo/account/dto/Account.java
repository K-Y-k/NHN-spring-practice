package com.example.demo.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@AllArgsConstructor
public class Account {
    @JsonProperty("아이디")
    long id;
    @JsonProperty("비밀번호")
    String password;
    @JsonProperty("이름")
    String name;
}
