package com.tfg.game.testSteps.listGames;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

@Component
public class ReturnFromTheListGamesStep extends AbstractPostLineStep {

    private final NavigatorTestView navigatorTestView;

    public ReturnFromTheListGamesStep(NavigatorTestView navigatorTestView) {
        this.navigatorTestView = navigatorTestView;
    }

    @Override
    protected String getRegex() {
        return "Return from the list games";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        navigatorTestView.popScreenName();
    }
}
