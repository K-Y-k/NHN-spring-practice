package com.nhnacademy.springbootmvc.controller;

import com.nhnacademy.springbootmvc.domain.Post;
import com.nhnacademy.springbootmvc.exception.PostNotFoundException;
import com.nhnacademy.springbootmvc.exception.UserNotFoundException;
import com.nhnacademy.springbootmvc.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/post")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    /**
     * 이 컨트롤러 내에서만 적용된 예외 처리
     */
    @ExceptionHandler(PostNotFoundException.class)
    public String notFoundHandleException(Exception ex, Model model) {
        log.error("Not found ", ex);
        model.addAttribute("exception", ex);
        return "error";
    }
    
    /**
     * 공통 처리 및 데이터 메소드
     */
    @ModelAttribute("post")
    public Post getPost(@PathVariable("postId") long postId) {
        Post post = postRepository.getPost(postId);
        if (Objects.isNull(post)) {
            throw new PostNotFoundException();
        }

        return post;
    }

    // TODO #2: 게시물 조회 구현
    @GetMapping("/{postId}")
    public String viewPost() {
        return "postView";
    }

    // TODO #3: 게시물 수정 form 구현
    @GetMapping("/{postId}/modify")
    public String postModifyForm() {
        return "postModify";
    }
}
