package com.tfg.game.testSteps.dices;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.game.EntityTestView;
import com.tfg.game.testSteps.game.GameTestView;
import com.tfg.game.testSteps.owneds.OwnedTestView;
import com.tfg.game.testSteps.player.PlayerTestView;
import org.springframework.stereotype.Component;

@Component
public class RollDicesStep extends AbstractPostLineStep {

    private final GameTestView gameTestView;
    private final PlayerTestView playerTestView;
    private final EntityTestView entityTestView;
    private final DicesTestView dicesTestView;

    public RollDicesStep(GameTestView gameTestView, PlayerTestView playerTestView, EntityTestView entityTestView, DicesTestView dicesTestView) {
        this.gameTestView = gameTestView;
        this.playerTestView = playerTestView;
        this.entityTestView = entityTestView;
        this.dicesTestView = dicesTestView;
    }

    @Override
    protected String getRegex() {
        return "Roll dices";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var player = playerTestView.getPlayerName();
        var entityId = dicesTestView.getDicesId();

        entityTestView.post("dice", entityId, player, "roll");
    }
}
