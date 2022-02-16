package com.drpicox.game.blog.api;

import com.drpicox.game.blog.Post;

import java.util.ArrayList;
import java.util.List;

public class ListPostsResponse {

    private List<ListPostsResponseEntry> posts = new ArrayList<>();

    public void addPost(Post post) {
        var entry = new ListPostsResponseEntry(post);
        posts.add(entry);
    }

    public List<ListPostsResponseEntry> getPosts() {
        return posts;
    }
}
