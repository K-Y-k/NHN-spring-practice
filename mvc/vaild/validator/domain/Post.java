package com.nhnacademy.springbootmvc.domain;

import lombok.Getter;
import lombok.Setter;

public class Post {
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String content;

    public static Post create(String title, String content) {
        return new Post(title, content);
    }

    private Post (String title, String content) {
        this.title = title;
        this.content = content;
    }

}
