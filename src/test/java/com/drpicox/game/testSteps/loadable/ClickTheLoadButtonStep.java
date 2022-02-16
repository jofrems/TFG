package com.drpicox.game.testSteps.loadable;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class ClickTheLoadButtonStep extends AbstractPostLineStep {

    private final LoadableTestView loadableTestView;

    public ClickTheLoadButtonStep(LoadableTestView loadableTestView) {
        this.loadableTestView = loadableTestView;
    }

    @Override
    protected String getRegex() {
        return "Click the load button";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        loadableTestView.submitLoad();
    }
}
