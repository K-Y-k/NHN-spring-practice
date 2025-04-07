package com.nhnacademy.springbootmvc.controller;

import com.nhnacademy.springbootmvc.domain.Post;
import com.nhnacademy.springbootmvc.domain.PostRegisterRequest;
import com.nhnacademy.springbootmvc.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/post/register")
public class PostRegisterController {
    private final PostRepository postRepository;

    public PostRegisterController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public String userRegisterForm() {
        return "postRegister";
    }

    // TODO #1: 게시물 등록 처리 구현
    @PostMapping
    public ModelAndView registerPost(@ModelAttribute PostRegisterRequest postRegisterRequest) {
        String title = postRegisterRequest.getTitle();
        String content = postRegisterRequest.getContent();

        Post registerPost = postRepository.register(title, content);

        ModelAndView mav = new ModelAndView("postView");
        mav.addObject("post", registerPost);

        return mav;
    }

}
