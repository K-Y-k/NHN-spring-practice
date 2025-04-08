package com.nhnacademy.springbootmvc.repository;

import com.nhnacademy.springbootmvc.domain.User;
import com.nhnacademy.springbootmvc.exception.UserAlreadyExistsException;
import com.nhnacademy.springbootmvc.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> userMap = new HashMap<>();

    public UserRepositoryImpl() {
        userMap.put("tom", User.create("tom", "1234"));
        userMap.put("jake", User.create("jake", "5678"));
        userMap.put("ethan", User.create("ethan", "1010"));
    }

    @Override
    public boolean exists(String id) {
        return userMap.containsKey(id);
    }

    @Override
    public boolean matches(String id, String password) {
        return Optional.ofNullable(getUser(id))
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public List<User> getUsers() {
        return userMap.values().stream().toList();
    }

    @Override
    public User getUser(String id) {
        return userMap.get(id);
    }

    @Override
    public User addUser(String id, String password) {
        // TODO #9: 이름 기본값 추가
        return addUser(id, password, 0, "admin");
    }

    @Override
    public User addUser(String id, String password, int age, String name) {
        if (exists(id)) {
            throw new UserAlreadyExistsException();
        }

        User user = User.create(id, password);
        user.setAge(age);
        // TODO #8: 이름 추가
        user.setName(name);
        userMap.put(id, user);

        return user;
    }

    // TODO #14: 수정 구현
    @Override
    public void modify(User user) {
        User dbUser = getUser(user.getId());
        if (Objects.isNull(dbUser)) {
            throw new UserNotFoundException();
        }

        dbUser.setAge(user.getAge());
        dbUser.setName(user.getName());
    }
}
