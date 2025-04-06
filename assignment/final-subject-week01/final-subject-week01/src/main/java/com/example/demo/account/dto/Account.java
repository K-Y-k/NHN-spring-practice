package com.example.demo.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @JsonProperty("아이디")
    long id;
    @JsonProperty("비밀번호")
    String password;
    @JsonProperty("이름")
    String name;
}
