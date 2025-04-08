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

    // TODO #3: 직접 구현한 `Validator` 추가
    private final PostRegisterRequestValidator validator;

    // TODO #3: 생성자에 직접 구현한 `Validator` 추가
    public PostRegisterController(PostRepository postRepository, PostRegisterRequestValidator validator) {
        this.postRepository = postRepository;
        this.validator = validator;
    }

    // TODO #2: `@InitBinder`를 통해 Validator 지정
    @InitBinder("postRegisterRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping
    public String userRegisterForm() {
        return "postRegister";
    }

    // TODO #3: `@Valid` 또는 `@Validated` annotation 적용
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
}
