package com.nhnacademy.springbootmvc.repository;

import com.nhnacademy.springbootmvc.domain.User;

import java.util.List;

public interface UserRepository {
    List<User> getUsers();
}
