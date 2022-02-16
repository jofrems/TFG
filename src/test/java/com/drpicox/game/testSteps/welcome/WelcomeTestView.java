package com.drpicox.game.testSteps.welcome;

import com.drpicox.game.testSteps.navigator.NavigableScreen;
import org.springframework.stereotype.Component;

@Component
public class WelcomeTestView implements NavigableScreen {

    @Override
    public String getScreenName() {
        return "welcome";
    }

    @Override
    public void show() {
    }
}
