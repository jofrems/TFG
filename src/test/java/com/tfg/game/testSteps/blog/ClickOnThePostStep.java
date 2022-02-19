package com.tfg.game.testSteps.blog;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class ClickOnThePostStep extends AbstractPostLineStep {

    private final BlogTestView blogTestView;
    private final PostTestView postTestView;

    public ClickOnThePostStep(BlogTestView blogTestView, PostTestView postTestView) {
        this.blogTestView = blogTestView;
        this.postTestView = postTestView;
    }

    @Override
    protected String getRegex() {
        return "Click on the post \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var wantedTitle = match[1];
        var posts = blogTestView.getListPostsResponse();
        var wantedPost = posts.stream().filter(p -> p.getTitle().equals(wantedTitle)).findAny().get();
        var wantedPostId = wantedPost.getId();

        postTestView.fetchPost(wantedPostId);
    }
}
