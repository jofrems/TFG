package com.tfg.game.testSteps.blog;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

@Component
public class ClickInTheGoBackToTheBlogStep extends AbstractPostLineStep {

    private final NavigatorTestView navigatorTestView;

    public ClickInTheGoBackToTheBlogStep(NavigatorTestView navigatorTestView) {
        this.navigatorTestView = navigatorTestView;
    }

    @Override
    protected String getRegex() {
        return "Click in the go back to the blog";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        navigatorTestView.popScreenName();
    }
}
