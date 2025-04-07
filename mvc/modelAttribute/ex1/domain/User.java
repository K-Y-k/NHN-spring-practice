package com.nhnacademy.springbootmvc.domain;

import lombok.Getter;
import lombok.Setter;

// TODO #1: User 클래스에 age 필드 추가
public class User {
    @Getter
    private final String id;

    @Getter
    private final String password;

    @Getter
    @Setter
    private int age;

    public static User create(String id, String password) {
        return new User(id, password);
    }

    private User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    // TODO #6: 비밀번호 마스킹 처리
    private static final String MASK = "*****";

    public static User constructPasswordMaskedUser(User user) {
        User newUser = User.create(user.getId(), MASK);
        newUser.setAge(user.getAge());

        return newUser;
    }

}