package com.nhnacademy.finalsubjectweek02.inquiry.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class InquiryRequest {
    private String classification;
    private String title;
    private String content;
}
