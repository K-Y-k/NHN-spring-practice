package com.nhnacademy.restcontrollerpractice.step2.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    ADMIN, USER;

    /**
     * JSON -> 객체로 역직렬화 할 때 사용
     */
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
     * 객체 -> JSON으로 직렬화 할 때 사용
     */
    @JsonValue
    public String toJson(){
        return this.name().toLowerCase();
    }
}
