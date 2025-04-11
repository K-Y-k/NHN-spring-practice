package com.nhnacademy.finalsubjectweek02.login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {
    String id;
    String password;
}
