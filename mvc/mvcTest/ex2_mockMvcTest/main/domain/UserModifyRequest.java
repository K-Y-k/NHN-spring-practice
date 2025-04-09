package com.nhnacademy.springbootmvc.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UserModifyRequest {
    @NotNull
    @Size(max = 50)
    String name;

    @Min(0)
    int age;
}
