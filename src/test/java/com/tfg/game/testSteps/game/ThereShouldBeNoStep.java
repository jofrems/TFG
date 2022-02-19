package com.tfg.game.testSteps.game;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

@Component
public class ThereShouldBeNoStep extends AbstractPostLineStep {

    private final EntityTestView entityTestView;

    public ThereShouldBeNoStep(EntityTestView entityTestView) {
        this.entityTestView = entityTestView;
    }

    @Override
    protected String getRegex() {
        return "There should be no ([a-z ]+)";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var entityPropertyKey = PrettyKey.getKey(match[1]);

        var entity = entityTestView.getEntity();
        var value = entity.get(entityPropertyKey);
        assertWithMessage("The entity '"+entity.getId()+"' should have no '"+entityPropertyKey+"' property.")
                .that(value).isNull();
    }
}
