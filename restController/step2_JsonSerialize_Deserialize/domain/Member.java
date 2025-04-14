package com.nhnacademy.restcontrollerpractice.step2.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class Member {
    private String name;

    /**
     * JsonSerialize는 객체 -> JSON 데이터로 직렬화할 때 사용한다.
     * @JsonSerialize(using = ToStringSerializer.class)는
     * JSON으로 변환될 때 해당 필드를 toString으로 해주는 것
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer age;

    @JsonSerialize(using = ToStringSerializer.class)
    private Role role;

    /**
     * @JsonProperty는
     * - 객체 필드명을 예약어와 겹쳐 제대로 명칭을 주지 못할 때 사용
     * - 클라이언트 팀에서 정의한 필드명과 백엔드 팀에서 정의한 필드명이 다를 경우 사용
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("class")
    private ClassType clazz;

    public Member(String name, Integer age, Role role, ClassType clazz) {
        this.name = name;
        this.age = age;
        this.role = role;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Role getRole() {
        return role;
    }

    public ClassType getClazz() {
        return clazz;
    }

    /**
     * toString()를 활용하여 변환 방식
     */
    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", role=" + this.role.name().toLowerCase() +
                ", class=" + clazz +
                '}';
    }
}
