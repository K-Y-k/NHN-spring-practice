package com.nhnacademy.restcontrollerpractice.redis.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private String id;
    private String name;

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer age;

    @JsonSerialize(using = ToStringSerializer.class)
    private Role role;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("class")
    private ClassType clazz;

}
