package com.tfg.game.blog.api;

import com.tfg.game.blog.BlogController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class BlogApi {

    private final BlogController blogController;

    public BlogApi(BlogController blogController) {
        this.blogController = blogController;
    }

    @GetMapping
    public ListPostsResponse listPosts() {
        var list = blogController.findAll();
        var result = new ListPostsResponse();
        list.forEach(post -> result.addPost(post));
        return result;
    }

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable String postId) throws Exception {
        var post = blogController.findPost(postId).orElseThrow();
        var response = new PostResponse(post);
        return response;
    }
}
