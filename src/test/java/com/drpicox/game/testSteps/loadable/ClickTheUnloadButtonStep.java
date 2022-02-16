package com.drpicox.game.testSteps.loadable;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class ClickTheUnloadButtonStep extends AbstractPostLineStep {

    private final LoadableTestView loadableTestView;

    public ClickTheUnloadButtonStep(LoadableTestView loadableTestView) {
        this.loadableTestView = loadableTestView;
    }

    @Override
    protected String getRegex() {
        return "Click the unload button";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        loadableTestView.submitUnload();
    }
}
