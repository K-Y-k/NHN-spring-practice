package com.nhnacademy.subjectweek02.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class StudentModifyRequest {
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
