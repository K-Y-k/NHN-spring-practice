package com.nhnacademy.finalsubjectweek02.admin.domain;

import com.nhnacademy.finalsubjectweek02.customer.domain.User;
import lombok.Getter;

@Getter
public class Admin extends User {
    public Admin(String id, String password, String name) {
        super(id, password, name);
    }
}
