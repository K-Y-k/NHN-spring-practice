package com.nhnacademy.springbootmvc.repository;

import com.nhnacademy.springbootmvc.domain.User;

import java.util.List;

public interface UserRepository {
    boolean exists(String id);
    boolean matches(String id, String password);
    List<User> getUsers();
    User getUser(String id);
    User addUser(String id, String password);

    // TODO #7: 이름 추가
    User addUser(String id, String password, int age, String name);

    // TODO #13: 수정용 API 추가
    void modify(User user);
}
