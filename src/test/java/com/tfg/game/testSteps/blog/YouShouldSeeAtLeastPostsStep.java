package com.tfg.game.testSteps.blog;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class YouShouldSeeAtLeastPostsStep extends AbstractPostLineStep {

    private final BlogTestView blogTestView;

    public YouShouldSeeAtLeastPostsStep(BlogTestView blogTestView) {
        this.blogTestView = blogTestView;
    }

    @Override
    protected String getRegex() {
        return "You should see at least (\\d+) posts";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var expectedQuantity = Integer.parseInt(match[1]);
        var posts = blogTestView.getListPostsResponse();
        assertThat(posts.size()).isAtLeast(expectedQuantity);
    }
}
