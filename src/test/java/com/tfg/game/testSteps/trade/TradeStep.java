package com.tfg.game.testSteps.trade;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.game.EntityTestView;
import com.tfg.game.testSteps.game.GameTestView;
import com.tfg.game.testSteps.owneds.OwnedTestView;
import com.tfg.game.testSteps.player.PlayerTestView;
import org.springframework.stereotype.Component;

@Component
public class TradeStep extends AbstractPostLineStep {

    private final GameTestView gameTestView;
    private final PlayerTestView playerTestView;
    private final EntityTestView entityTestView;
    private final OwnedTestView ownedTestView;


    public TradeStep(GameTestView gameTestView, PlayerTestView playerTestView, EntityTestView entityTestView, OwnedTestView ownedTestView) {
        this.gameTestView = gameTestView;
        this.playerTestView = playerTestView;
        this.entityTestView = entityTestView;
        this.ownedTestView = ownedTestView;
    }

    @Override
    protected String getRegex() {
        return "Trade \"([^\"]+)\" for \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var player = playerTestView.getPlayerName();
        var entityId = ownedTestView.getVertexId();

        entityTestView.post("resources", entityId, player, "trade");
    }
}
