package com.nhnacademy.subjectweek02.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentRegisterRequest {
    private String id;
    private String password;
    private String name;
    private String email;
    private int score;
    private String comment;
}
