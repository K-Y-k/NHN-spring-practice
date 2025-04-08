package com.nhnacademy.springbootmvc.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

// TODO #11: 수정 요청 객체
@Value
public class UserModifyRequest {
    /**
     * Bean Validation Annotation 적용
     */
    @NotNull
    @Size(max = 50)
    String name;

    @Min(0)
    int age;
}
