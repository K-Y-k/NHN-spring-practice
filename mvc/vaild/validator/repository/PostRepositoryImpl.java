package com.nhnacademy.springbootmvc.repository;

import com.nhnacademy.springbootmvc.domain.Post;
import com.nhnacademy.springbootmvc.exception.PostNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Repository("postRepository")
public class PostRepositoryImpl implements PostRepository {
    private final Map<Long, Post> posts = new HashMap<>();

    @Override
    public boolean exists(long id) {
        return posts.containsKey(id);
    }

    @Override
    public Post register(String title, String content) {
        long id = posts.keySet()
                .stream()
                .max(Comparator.comparing(Function.identity()))
                .map(l -> l + 1)
                .orElse(1L);

        Post post = Post.create(title, content);
        post.setId(id);

        posts.put(id, post);

        return post;
    }

    @Override
    public Post getPost(long id) {
        return exists(id) ? posts.get(id) : null;
    }


    @Override
    public void modify(Post post) {
        Post dbPost = getPost(post.getId());
        if (Objects.isNull(dbPost)) {
            throw new PostNotFoundException();
        }

        dbPost.setTitle(post.getTitle());
        dbPost.setContent(post.getContent());
    }
}