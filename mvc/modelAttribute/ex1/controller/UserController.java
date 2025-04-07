package com.nhnacademy.springbootmvc.controller;

import com.nhnacademy.springbootmvc.domain.User;
import com.nhnacademy.springbootmvc.exception.UserNotFoundException;
import com.nhnacademy.springbootmvc.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;

// TODO #1: UserController
//          GET /user/{userId}             : 사용자 정보 조회
//          GET /user/{userId}/modify   : 사용자 정보 수정 form
@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 메서드에 선언된 경우, 모든 @RequestMapping에 앞서 호출되어 공통 속성을 제공한다.
     * - 공통적으로 넣어줘야 하는 상황에만 유용하다.
     * - 이와 관련한 유효성 검증에 대한 처리도 이 메소드 하나로 통일 가능해진다.
     *
     * 여기서의 예시는 user에 User 객체 값을 넣어준다.
     * name 속성 생략시 소문자인 객체명이다.
     */
    @ModelAttribute("user")
    public User getUser(@PathVariable("userId") String id, Model model) {
        User user = userRepository.getUser(id);
        if (Objects.isNull(user)) {
            model.addAttribute("exception", new UserNotFoundException());
        }

        return user;
    }

    // 여기서는 @PathVariable("userId") String id를 공통적으로 받는 파라미터라서 이 메소드는 안된다.
    //@GetMapping("/users")
    //public String getUsers(Model model) {
    //    List<User> users = userRepository.getUsers();
    //    model.addAttribute("users", users);
    //    return "users";
    //}

    @GetMapping("/user/{userId}")
    public String getUser() {
                                //(@PathVariable("userId") String id, Model model) {
        //User user = userRepository.getUser(id);
        //model.addAttribute("user", user);

        return "user";
    }

    @GetMapping("/user/{userId}/modify")
    public String userModifyForm() {
                                            //(@PathVariable("userId") String id, Model model) {
        //User user = userRepository.getUser(userId);
        //if (Objects.isNull(user)) {
        //    model.addAttribute("exception", new UserNotFoundException());
        //    return "error";
        //}
        //model.addAttribute("user", user);

        return "userModify";
    }
}
