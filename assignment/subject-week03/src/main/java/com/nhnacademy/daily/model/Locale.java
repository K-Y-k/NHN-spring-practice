package com.nhnacademy.daily.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Locale {
    KO, EN, JP;

    // 역직렬화
    @JsonCreator
    public static Locale fromString(String str){
        for (Locale locale : Locale.values()) {
            if (locale.name().equalsIgnoreCase(str)) {
                return locale;
            }
        }
        //default
        return KO;
    }

    /**
     * JSON으로 직렬화 할 때 사용
     * 즉, GET으로 JSON 데이터로 변환해서 반환할 때 필요
     */
    @JsonValue
    public String toJson(){
        return this.name().toLowerCase();
    }
}
