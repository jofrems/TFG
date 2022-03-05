package com.tfg.game.testSteps.tiles;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.game.EntityTestView;
import com.tfg.game.testSteps.game.GameTestView;
import com.tfg.game.testSteps.game.PrettyKey;
import com.tfg.game.testSteps.inventory.InventoryTestView;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;

@Component
public class TheShouldBeTilesStep extends AbstractPostLineStep {

    private final EntityTestView entityTestView;
    private final TilesTestView tilesTestView;
    private final GameTestView gameTestView;

    public TheShouldBeTilesStep(EntityTestView entityTestView, TilesTestView tilesTestView, GameTestView gameTestView) {
        this.entityTestView = entityTestView;
        this.tilesTestView = tilesTestView;
        this.gameTestView = gameTestView;
    }

    @Override
    protected String getRegex() {
        return "There should be (\\d+) tiles";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        if (match == null) return;
        var expectedValue = Integer.parseInt(match[1]);
        var value = tilesTestView.countTiles();

        assertThat(value).isEqualTo(expectedValue);
    }

}
