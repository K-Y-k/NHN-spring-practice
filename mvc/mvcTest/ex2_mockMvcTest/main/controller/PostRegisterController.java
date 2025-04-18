package com.nhnacademy.springbootmvc.controller;

import com.nhnacademy.springbootmvc.domain.Post;
import com.nhnacademy.springbootmvc.domain.PostRegisterRequest;
import com.nhnacademy.springbootmvc.exception.ValidationFailedException;
import com.nhnacademy.springbootmvc.repository.PostRepository;
import com.nhnacademy.springbootmvc.validator.PostRegisterRequestValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/post/register")
public class PostRegisterController {
    private final PostRepository postRepository;

    private final PostRegisterRequestValidator validator;

    public PostRegisterController(PostRepository postRepository, PostRegisterRequestValidator validator) {
        this.postRepository = postRepository;
        this.validator = validator;
    }

    @GetMapping
    public String userRegisterForm() {
        return "postRegister";
    }

    @PostMapping
    public ModelAndView registerPost(@Validated @ModelAttribute PostRegisterRequest postRequest,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Post post = postRepository.register(postRequest.getTitle(), postRequest.getContent());

        ModelAndView mav = new ModelAndView("postView");
        mav.addObject("post", post);

        return mav;
    }

    @InitBinder("postRegisterRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

}
