package com.nhnacademy.finalsubjectweek02.answer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class AnswerRequest {
    @Length(min = 1, max = 40000)
    private String comment;
}
