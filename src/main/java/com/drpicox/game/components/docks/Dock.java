package com.drpicox.game.components.docks;

import com.drpicox.game.ecs.EcsComponent;
import com.drpicox.game.games.Game;

import javax.persistence.Entity;

@Entity
public class Dock extends EcsComponent {
    public Dock(String entityId, Game game) {
        super(entityId, game);
    }

    protected Dock() {}
}
