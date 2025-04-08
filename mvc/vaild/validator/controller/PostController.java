package com.nhnacademy.springbootmvc.controller;

import com.nhnacademy.springbootmvc.domain.Post;
import com.nhnacademy.springbootmvc.domain.PostModifyRequest;
import com.nhnacademy.springbootmvc.domain.User;
import com.nhnacademy.springbootmvc.exception.PostNotFoundException;
import com.nhnacademy.springbootmvc.exception.ValidationFailedException;
import com.nhnacademy.springbootmvc.repository.PostRepository;
import com.nhnacademy.springbootmvc.validator.PostModifyRequestValidator;
import com.nhnacademy.springbootmvc.validator.PostRegisterRequestValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostRepository postRepository;

    /**
     * 1.직접 구현한 Validator 선언
     */
    private final PostModifyRequestValidator validator;

    /**
     * 2.생성자 주입에 직접 구현한 Validator 주입
     */
    public PostController(PostRepository postRepository,  PostModifyRequestValidator validator) {
        this.postRepository = postRepository;
        this.validator = validator;
    }

    /**
     * 3.Validator 설정
     */
    @InitBinder("postModifyRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);

    }

    @ModelAttribute("post")
    public Post getPost(@PathVariable("postId") long postId) {
        Post post = postRepository.getPost(postId);
        if (Objects.isNull(post)) {
            throw new PostNotFoundException();
        }

        return post;
    }

    @GetMapping("/{postId}")
    public String viewPost() {
        return "postView";
    }

    @GetMapping("/{postId}/modify")
    public String postModifyForm() {
        return "postModify";
    }

    /**
     * 4.검증 객체에 @Valid 적용 및 BindingResult로 검증
     */
    @PostMapping("/{postId}/modify")
    public String postModify(@ModelAttribute Post post,
                             @Valid @ModelAttribute PostModifyRequest postRequest,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());

        postRepository.modify(post);

        model.addAttribute("post", post);
        return "postView";
    }
}
