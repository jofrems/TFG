package com.drpicox.game.components.typeds;

import com.drpicox.game.ecs.EcsComponent;
import com.drpicox.game.games.Game;

import javax.persistence.Entity;

@Entity
public class Typed extends EcsComponent {
    private String type;

    public Typed(String entityId, Game game, String type) {
        super(entityId, game);
        this.type = type;
    }

    protected Typed() {}

    public String getType() {
        return type;
    }

    public boolean isType(String type) {
        return this.type.equals(type);
    }
}
