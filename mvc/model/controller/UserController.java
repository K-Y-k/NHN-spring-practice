package com.nhnacademy.springbootmvc.controller;

import com.nhnacademy.springbootmvc.domain.User;
import com.nhnacademy.springbootmvc.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Model 방식
     */
    @GetMapping("/model")
    public String getUsers(Model model) { /* TODO 1: 파라미터로 적절한 모델 type 넣기 */
        List<User> users = userRepository.getUsers();

        //TODO 2: user 목록을 모델의 attribute 추가
        model.addAttribute("users", users);

        return "users";
    }


    /**
     * ModelMap 방식
     * - ModelMap model 또는 Map<String, Object> model
     * - ModelMap model 또는 Map<String, Object> model를 파라미터로 전달 받으면 내부에서 모델로 받아서 진행
     */
    @GetMapping("/modelMap")
    public String getUsersModelMap(Map<String, Object> model) { /* TODO 1: 파라미터로 적절한 모델 type 넣기 */
        List<User> users = userRepository.getUsers();

        //TODO 2: user 목록을 모델의 attribute 추가
        model.put("users", users);

        return "users";
    }

    /**
     * ModelAndView 방식
     */
    @GetMapping("/modelAndView")
    public ModelAndView getUsersModelAndView() { /* TODO 1: 반환 타입을 ModelAndView로 넣기 */
        List<User> users = userRepository.getUsers();

        //TODO 2: user 목록을 모델의 attribute 추가
        ModelAndView mav = new ModelAndView("users"); // 반환을 ModelAndView로 한 것이므로 "users"라는 뷰로 선언한 것
        mav.addObject("users", users);

        return mav;
    }
}
