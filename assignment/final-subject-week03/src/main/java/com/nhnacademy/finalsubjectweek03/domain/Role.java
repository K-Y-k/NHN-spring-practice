package com.nhnacademy.finalsubjectweek03.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    ADMIN, MEMBER, GOOGLE;

    /// 역직렬화
    @JsonCreator
    public static Role fromString(String str){
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(str)) {
                return role;
            }
        }
        //default
        return MEMBER;
    }

    /**
     * 해당 Enum 필드를 가진 객체에서 직렬화시 @JsonSerialize(using = ToStringSerializer.class)로 적용하기 위해
     * toString 방식
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
