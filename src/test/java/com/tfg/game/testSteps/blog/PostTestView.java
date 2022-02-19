package com.tfg.game.testSteps.blog;

import com.tfg.game.blog.Post;
import com.tfg.game.testPost.SnapshotService;
import com.tfg.game.testSteps.navigator.NavigableScreen;
import com.tfg.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

@Component
public class PostTestView implements NavigableScreen {

    private final NavigatorTestView navigatorTestView;
    private final SnapshotService snapshotService;

    public PostTestView(NavigatorTestView navigatorTestView, SnapshotService snapshotService) {
        this.navigatorTestView = navigatorTestView;
        this.snapshotService = snapshotService;
    }

    private Post post;

    /////////// --- NavigableScreen

    @Override
    public String getScreenName() {
        return "post";
    }

    @Override
    public void show() {}

    /////////// --- RestMethods

    public void fetchPost(String postId) {
        this.post = snapshotService.get("/api/v1/posts/" + postId, null, Post.class);
        navigatorTestView.pushScreenName("post");
    }

    public Post getPost() {
        return post;
    }
}
