package com.tfg.game.testSteps.game;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.inventory.InventoryTestView;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class TheShouldBeInventoryStep extends AbstractPostLineStep {

    private final EntityTestView entityTestView;
    private final InventoryTestView inventoryTestView;

    public TheShouldBeInventoryStep(EntityTestView entityTestView, InventoryTestView inventoryTestView) {
        this.entityTestView = entityTestView;
        this.inventoryTestView = inventoryTestView;
    }

    @Override
    protected String getRegex() {
        return "There should be (\\d+) ([a-z ]+) in the inventory";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var entityPropertyKey = PrettyKey.getKey(match[2]);

        mayRunWithNumber(entityPropertyKey, match[1]);
    }

    private void mayRunWithNumber(String key, String match) {
        if (match == null) return;
        var expectedValue = Integer.parseInt(match);

        String inventoryId = inventoryTestView.getInventoryId();

        var value = entityTestView.getEntityPropertyInt(inventoryId, key);
        assertThat(value).isEqualTo(expectedValue);
    }

}
