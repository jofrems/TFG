package com.tfg.game.blog.api;

import com.tfg.game.blog.Post;

import java.util.Map;

public class ListPostsResponseEntry {
    private final String id;
    private final String title;
    private final int bodyLineNumber;
    private final Map<String,String> frontMatter;

    public ListPostsResponseEntry(Post post) {
        id = post.getId();
        title = post.getTitle();
        frontMatter = post.getFrontMatter();
        bodyLineNumber = post.getBodyLineNumber();
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }
}
