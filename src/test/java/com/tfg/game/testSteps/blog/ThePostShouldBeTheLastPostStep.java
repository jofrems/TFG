package com.tfg.game.testSteps.blog;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class ThePostShouldBeTheLastPostStep extends AbstractPostLineStep {

    private final BlogTestView blogTestView;

    public ThePostShouldBeTheLastPostStep(BlogTestView blogTestView) {
        this.blogTestView = blogTestView;
    }

    @Override
    protected String getRegex() {
        return "The post \"([^\"]+)\" should be the last post";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var expectedTitle = match[1];
        var posts = blogTestView.getListPostsResponse();
        var lastPost = posts.get(posts.size() - 1);
        assertThat(lastPost.getTitle()).contains(expectedTitle);
    }
}
