package com.nhnacademy.restcontrollerpractice.redis.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberDto {
    private String id;
    private String name;

    @JsonCreator
    public MemberDto(@JsonProperty("id") String id,
                     @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer age;

    @JsonSerialize(using = ToStringSerializer.class)
    private Role role;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("class")
    private ClassType clazz;
}