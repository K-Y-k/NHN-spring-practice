package com.nhnacademy.daily.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    private String id;
    private String name;

    private Integer age;

    private String clazz;

    private Locale locale;

    @JsonCreator
    public Member(@JsonProperty("id") String id,
                  @JsonProperty("name") String name,
                  @JsonProperty("age") Integer age,
                  @JsonProperty("class") String clazz,
                  @JsonProperty("locale") Locale locale)  {
        this.id = id;
        this.name = name;
        this.age = age;
        this.clazz = clazz;
        this.locale = locale;
    }
}
