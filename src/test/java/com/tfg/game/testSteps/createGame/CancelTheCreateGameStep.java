package com.tfg.game.testSteps.createGame;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

@Component
public class CancelTheCreateGameStep extends AbstractPostLineStep {

    private final NavigatorTestView navigatorTestView;

    public CancelTheCreateGameStep(NavigatorTestView navigatorTestView) {
        this.navigatorTestView = navigatorTestView;
    }

    @Override
    protected String getRegex() {
        return "Cancel the create game";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        navigatorTestView.popScreenName();
    }
}
