package com.tfg.game.testSteps.blog;

import com.tfg.game.blog.Post;
import com.tfg.game.blog.api.ListPostsResponse;
import com.tfg.game.blog.api.ListPostsResponseEntry;
import com.tfg.game.testPost.SnapshotService;
import com.tfg.game.testSteps.navigator.NavigableScreen;
import com.tfg.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogTestView implements NavigableScreen {

    private final NavigatorTestView navigatorTestView;
    private final SnapshotService snapshotService;

    public BlogTestView(NavigatorTestView navigatorTestView, SnapshotService snapshotService) {
        this.navigatorTestView = navigatorTestView;
        this.snapshotService = snapshotService;
    }

    private ListPostsResponse listPostsResponse;
    private Post post;

    /////////// --- NavigableScreen

    @Override
    public String getScreenName() {
        return "blog";
    }

    @Override
    public void show() {}

    /////////// --- RestMethods

    public void fetchPosts() {
        listPostsResponse = snapshotService.get("/api/v1/posts", null, ListPostsResponse.class);
        navigatorTestView.pushScreenName("blog");
    }

    public List<ListPostsResponseEntry> getListPostsResponse() {
        return listPostsResponse.getPosts();
    }

    public void fetchPost(String postId) {
        this.post = snapshotService.get("/api/v1/posts/" + postId, null, Post.class);
        navigatorTestView.pushScreenName("post");
    }
}
