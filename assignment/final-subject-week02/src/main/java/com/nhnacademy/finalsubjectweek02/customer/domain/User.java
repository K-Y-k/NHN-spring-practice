package com.nhnacademy.finalsubjectweek02.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class User {
    private String id;
    private String password;
    private String name;
}
