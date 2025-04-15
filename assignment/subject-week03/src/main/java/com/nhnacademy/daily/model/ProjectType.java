package com.nhnacademy.daily.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProjectType {
    PUBLIC, PRIVATE;

    // 역직렬화
    @JsonCreator
    public static ProjectType fromString(String str){
        for (ProjectType projectType : ProjectType.values()) {
            if (projectType.name().equalsIgnoreCase(str)) {
                return projectType;
            }
        }
        //default
        return PUBLIC;
    }

    // 직렬화
    @JsonValue
    public String toLower() {
        return this.name().toLowerCase();
    }
}
