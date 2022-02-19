package com.tfg.game.testSteps.game;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class YouShouldBeAtAnEmptyScreenStep extends AbstractPostLineStep {

    private final TypedTestView typedTestView;
    private final NavigatorTestView navigatorTestView;
    private final EntityTestView entityTestView;

    public YouShouldBeAtAnEmptyScreenStep(TypedTestView typedTestView, NavigatorTestView navigatorTestView, EntityTestView entityTestView) {
        this.typedTestView = typedTestView;
        this.navigatorTestView = navigatorTestView;
        this.entityTestView = entityTestView;
    }

    @Override
    protected String getRegex() {
        return "You should be at an empty screen";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var screenName = navigatorTestView.peekScreenName();
        var entityId = navigatorTestView.peekId();

        var entity = entityTestView.getEntity();
        assertThat(screenName).isEqualTo("entity");
        assertThat(entity).isNull();
    }
}
