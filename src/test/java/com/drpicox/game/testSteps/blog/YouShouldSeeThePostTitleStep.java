package com.drpicox.game.testSteps.blog;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class YouShouldSeeThePostTitleStep extends AbstractPostLineStep {

    private final PostTestView postTestView;

    public YouShouldSeeThePostTitleStep(PostTestView postTestView) {
        this.postTestView = postTestView;
    }

    @Override
    protected String getRegex() {
        return "You should see the post title \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var expectedTitle = match[1];

        var post = postTestView.getPost();
        assertThat(post.getTitle()).isEqualTo(expectedTitle);
    }
}
