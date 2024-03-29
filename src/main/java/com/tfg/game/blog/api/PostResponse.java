package com.tfg.game.blog.api;

import com.tfg.game.blog.Post;

public class PostResponse {
    private final String id;
    private final String body;
    private final String title;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
    }
}
