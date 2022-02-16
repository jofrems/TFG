package com.drpicox.game.components.loadables;

import com.drpicox.game.ecs.EcsComponent;
import com.drpicox.game.games.Game;

import javax.persistence.Entity;

@Entity
public class Loadable extends EcsComponent {
    private int loadUnloadAmount;
    private String sourceEntityId;

    public Loadable(String entityId, Game game) {
        super(entityId, game);
    }

    protected Loadable() {}

    public int getLoadUnloadAmount() {
        return this.loadUnloadAmount;
    }

    public String getSourceEntityId() {
        return sourceEntityId;
    }

    void orderLoadUnload(int newLoadUnloadAmount, String sourceEntityId) {
        this.loadUnloadAmount = newLoadUnloadAmount;
        this.sourceEntityId = sourceEntityId;
    }

    void clearLoadUnloadOrder() {
        this.loadUnloadAmount = 0;
        this.sourceEntityId = null;
    }
}
