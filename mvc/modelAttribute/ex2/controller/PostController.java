package com.nhnacademy.springbootmvc.controller;

import com.nhnacademy.springbootmvc.domain.Post;
import com.nhnacademy.springbootmvc.domain.PostRegisterRequest;
import com.nhnacademy.springbootmvc.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * 공통적으로 적용한 getPost 메소드
     */
    @ModelAttribute
    public Post getPost(@PathVariable long postId) {
        return postRepository.getPost(postId);
    }

    // TODO #2: 게시물 조회 구현
    @GetMapping("/{postId}")
    public String viewPost() {
                          //(@PathVariable long postId, Model model) {
        //Post post = postRepository.getPost(postId);
        //model.addAttribute("post", post);

        return "postView";
    }

    // TODO #3: 게시물 수정 form 구현
    @GetMapping("/{postId}/modify")
    public String postModifyForm() {
                                //(@PathVariable long postId, Model model) {
        //Post post = postRepository.getPost(postId);
        //model.addAttribute("post", post);

        return "postModify";
    }
}
