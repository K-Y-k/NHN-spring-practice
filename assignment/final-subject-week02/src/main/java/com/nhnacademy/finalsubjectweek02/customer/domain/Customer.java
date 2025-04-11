package com.nhnacademy.finalsubjectweek02.customer.domain;


import lombok.Getter;

@Getter
public class Customer extends User {
    public Customer(String id, String password, String name) {
        super(id, password, name);
    }
}
