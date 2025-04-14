package com.nhnacademy.restcontrollerpractice.step3.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhnacademy.restcontrollerpractice.step2.domain.ClassType;
import com.nhnacademy.restcontrollerpractice.step2.domain.Role;

public class MemberDto {
    private String name;
    private Integer age;
    private Role role;
    @JsonProperty("class")
    private ClassType clazz;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Role getRole() {
        return role;
    }

    public ClassType getClazz() {
        return clazz;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setClazz(ClassType clazz) {
        this.clazz = clazz;
    }
}