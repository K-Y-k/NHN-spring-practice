package com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    ADMIN, USER;

    @JsonCreator
    public static Role fromString(String str){
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(str)) {
                return role;
            }
        }
        //default
        return USER;
    }

    @JsonValue
    public String toJson(){
        return this.name().toLowerCase();
    }
}
