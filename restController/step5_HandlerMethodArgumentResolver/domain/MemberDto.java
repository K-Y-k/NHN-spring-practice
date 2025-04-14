package com.nhnacademy.restcontrollerpractice.step5.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    /**
     * toString()를 활용하여 변환 방식
     */
    @Override
    public String toString() {
        return "MemberDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", role=" + this.role.name().toLowerCase() +
                ", clazz=" + clazz.toJson() +
                '}';
    }
}