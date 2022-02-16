package com.drpicox.game.testSteps.blog;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth8.assertThat;

@Component
public class YouShouldSeeThePostStep extends AbstractPostLineStep {

    private final BlogTestView blogTestView;

    public YouShouldSeeThePostStep(BlogTestView blogTestView) {
        this.blogTestView = blogTestView;
    }

    @Override
    protected String getRegex() {
        return "You should see the post \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var expectedTitle = match[1];
        var posts = blogTestView.getListPostsResponse();
        var titles = posts.stream().map(p -> p.getTitle());
        assertThat(titles).contains(expectedTitle);
    }
}
