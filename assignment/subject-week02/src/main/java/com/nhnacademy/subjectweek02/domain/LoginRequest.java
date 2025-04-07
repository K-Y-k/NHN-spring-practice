package com.nhnacademy.subjectweek02.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {
    String id;
    String password;
}
