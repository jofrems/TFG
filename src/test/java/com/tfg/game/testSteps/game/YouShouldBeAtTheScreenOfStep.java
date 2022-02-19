package com.tfg.game.testSteps.game;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class YouShouldBeAtTheScreenOfStep extends AbstractPostLineStep {

    private final TypedTestView typedTestView;
    private final NavigatorTestView navigatorTestView;
    private final EntityTestView entityTestView;

    public YouShouldBeAtTheScreenOfStep(TypedTestView typedTestView, NavigatorTestView navigatorTestView, EntityTestView entityTestView) {
        this.typedTestView = typedTestView;
        this.navigatorTestView = navigatorTestView;
        this.entityTestView = entityTestView;
    }

    @Override
    protected String getRegex() {
        return "You should be at the screen of a ([a-z ]+)";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var expectedScreenName = "entity";
        var expectedType = match[1].trim();

        var screenName = navigatorTestView.peekScreenName();
        var type = typedTestView.getType();

        assertThat(screenName).isEqualTo(expectedScreenName);
        assertThat(type).isEqualTo(expectedType);
    }
}
