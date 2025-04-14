package com.nhnacademy.restcontrollerpractice.step5.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ClassType {
    A, B, C;

    /**
     * JSON 문자열을 enum 타입으로 역직렬화 할 때 사용되는 커스텀 생성 로직을 정의한 것
     * - Enum에서 @JsonCreator를 사용하면 JSON이 "A"이면 ClassType.A로 자동 매핑됨.
     */
    @JsonCreator
    public static ClassType fromString(String str){
        for (ClassType value : ClassType.values()) {
            if (value.name().equalsIgnoreCase(str)) {
                return value;
            }
        }
        //default
        return A;
    }

    /**
     * @JsonValue를 활용하여 변환 방식
     */
    @JsonValue
    public String toJson(){
        return this.name().toLowerCase();
    }
}
