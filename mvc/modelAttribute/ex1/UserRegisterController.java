package com.nhnacademy.springbootmvc.controller;

import com.nhnacademy.springbootmvc.domain.User;
import com.nhnacademy.springbootmvc.domain.UserRegisterRequest;
import com.nhnacademy.springbootmvc.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/register")
public class UserRegisterController {
    private final UserRepository userRepository;

    public UserRegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // TODO #2: 사용자 등록 form 표시
    @GetMapping
    public String userRegisterForm() {
        return "userRegister";
    }

    // TODO #4: 사용자 등록 처리

    /**
     * 해당 객체가 받아오는 파라미터 변수명을 모두 일치 시키면 @ModelAttribute 생략 가능
     */
    @PostMapping
    public ModelAndView registerUser(UserRegisterRequest userRequest) {
        User user = userRepository.addUser(userRequest.getId(), userRequest.getPassword(), userRequest.getAge());

        ModelAndView mav = new ModelAndView("user");
        mav.addObject("user", User.constructPasswordMaskedUser(user));

        return mav;
    }
}
