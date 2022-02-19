package com.drpicox.game.testSteps.navigator;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class YouShouldBeAtTheScreenStep extends AbstractPostLineStep {

    private final NavigatorTestView navigatorTestView;

    public YouShouldBeAtTheScreenStep(NavigatorTestView navigatorTestView) {
        this.navigatorTestView = navigatorTestView;
    }

    @Override
    protected String getRegex() {
        return "You should be( back)? at the (.+) screen";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var screenName = match[2];

        assertThat(navigatorTestView.peekScreenName()).isEqualTo(screenName);
    }
}
