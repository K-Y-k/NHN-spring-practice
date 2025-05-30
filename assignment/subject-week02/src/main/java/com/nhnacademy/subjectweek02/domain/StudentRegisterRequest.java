package com.nhnacademy.subjectweek02.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class StudentRegisterRequest {
    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @Email
    private String email;

    @Max(100)
    private int score;

    @NotBlank
    @Length(min = 1, max = 200)
    private String comment;
}
