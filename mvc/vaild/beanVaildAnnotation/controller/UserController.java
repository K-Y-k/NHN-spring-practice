package com.nhnacademy.springbootmvc.controller;

import com.nhnacademy.springbootmvc.domain.User;
import com.nhnacademy.springbootmvc.domain.UserModifyRequest;
import com.nhnacademy.springbootmvc.exception.UserNotFoundException;
import com.nhnacademy.springbootmvc.exception.ValidationFailedException;
import com.nhnacademy.springbootmvc.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userRepository.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user/{userId}")
    public String getUser(Model model,
                          @PathVariable("userId") String id) {
        User user = userRepository.getUser(id);
        if (Objects.isNull(user)) {
            model.addAttribute("exception", new UserNotFoundException());
            return "error";
        }
        model.addAttribute("user", user);
        return "user";
    }

    // TODO #12: 수정 요청 처리
    /**
     * 1. Controller Method signature 에서
     *    요청 객체에 @Valid 또는 @Validated annotation을 적용하고
     *    인자로 BindingResult 또는 Errors 객체를 선언한 후
     */
    @PostMapping("/user/{userId}/modify")
    public String modifyUser(@ModelAttribute User user,                                // 기존 user 정보
                             @Valid @ModelAttribute UserModifyRequest userRequest,     // 수정 요청 객체
                             BindingResult bindingResult,
                             Model model) {
        /**
         * 2. Controller Method 본문에서
         *    선언한 BindingResult 또는 Errors 객체를 이용해서 validation 결과 확인
         */
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());

        userRepository.modify(user);

        model.addAttribute("user", user);
        return "userInfo";
    }
}
