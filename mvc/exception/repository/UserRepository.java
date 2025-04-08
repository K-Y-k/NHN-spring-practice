package com.nhnacademy.springbootmvc.repository;

import com.nhnacademy.springbootmvc.domain.User;

import java.util.List;

public interface UserRepository {
    boolean exists(String id);
    boolean matches(String id, String password);
    List<User> getUsers();
    User getUser(String id);
    User addUser(String id, String password);

    User addUser(String id, String password, int age);
}
