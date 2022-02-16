package com.drpicox.game.testSteps.sails;

import com.drpicox.game.testSteps.game.EntityTestView;
import org.springframework.stereotype.Component;

@Component
public class SailTestView {

    private final EntityTestView entityTestView;

    public SailTestView(EntityTestView entityTestView) {
        this.entityTestView = entityTestView;
    }

    public void submitSail() {
        entityTestView.post("sails", "sail");
    }

    public void submitHalt() {
        entityTestView.post("sails", "halt");
    }
}
