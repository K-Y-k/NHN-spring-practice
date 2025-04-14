package com.nhnacademy.restcontrollerpractice.step5.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    ADMIN, USER;

    @JsonCreator
    public static Role fromString(String str){
        for (Role value : Role.values()) {
            if (value.name().equalsIgnoreCase(str)) {
                return value;
            }
        }
        //default
        return ADMIN;
    }

    /**
     * @JsonValue로 소문자로 적용 방식
     */
    @JsonValue
    public String toJson(){
        return this.name().toLowerCase();
    }
}

