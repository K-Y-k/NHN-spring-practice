package com.nhnacademy.finalsubjectweek03.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private String id;
    private String name;
    private String password;
    private Integer age;
    private Role role;
}
