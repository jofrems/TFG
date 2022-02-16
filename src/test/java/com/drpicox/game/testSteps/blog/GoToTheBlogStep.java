package com.drpicox.game.testSteps.blog;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class GoToTheBlogStep extends AbstractPostLineStep {

    private final BlogTestView blogTestView;

    public GoToTheBlogStep(BlogTestView blogTestView) {
        this.blogTestView = blogTestView;
    }

    @Override
    protected String getRegex() {
        return "Go to the Blog";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        blogTestView.fetchPosts();
    }
}
