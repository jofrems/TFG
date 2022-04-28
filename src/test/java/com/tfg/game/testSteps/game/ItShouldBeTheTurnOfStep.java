package com.tfg.game.testSteps.game;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.inventory.InventoryTestView;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class ItShouldBeTheTurnOfStep extends AbstractPostLineStep {

    private final GameTestView gameTestView;
    private final InventoryTestView inventoryTestView;

    public ItShouldBeTheTurnOfStep(GameTestView gameTestView, InventoryTestView inventoryTestView) {
        this.gameTestView = gameTestView;
        this.inventoryTestView = inventoryTestView;
    }

    @Override
    protected String getRegex() {
        return "It should be the turn of \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var shouldBeCurrentName = PrettyKey.getKey(match[1]);

        var currentPlayerNameTurn = gameTestView.getGame().getPlayerNameTurn();
        assertThat(shouldBeCurrentName).isEqualTo(currentPlayerNameTurn);

    }
}
