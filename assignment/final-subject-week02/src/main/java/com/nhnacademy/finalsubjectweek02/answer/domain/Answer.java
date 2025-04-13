package com.nhnacademy.finalsubjectweek02.answer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Answer {
    private long inquiryId;
    private String adminId;
    private String comment;
    private String writer;
    private LocalDateTime createdDate;

}
