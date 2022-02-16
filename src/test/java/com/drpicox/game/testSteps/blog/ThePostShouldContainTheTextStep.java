package com.drpicox.game.testSteps.blog;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class ThePostShouldContainTheTextStep extends AbstractPostLineStep {

    private final PostTestView postTestView;

    public ThePostShouldContainTheTextStep(PostTestView postTestView) {
        this.postTestView = postTestView;
    }

    @Override
    protected String getRegex() {
        return "The post should contain the text \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var expectedContent = match[1];

        var post = postTestView.getPost();
        assertThat(post.getBody()).contains(expectedContent);
    }
}
