package com.nhnacademy.finalsubjectweek02.inquiry.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class InquiryRequest {
    private String classification;

    @Length(min = 2, max = 200)
    private String title;

    @NotBlank
    @Length(max = 40000)
    private String content;

    private List<MultipartFile> files;
}
