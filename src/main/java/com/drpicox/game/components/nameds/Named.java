package com.drpicox.game.components.nameds;

import com.drpicox.game.ecs.EcsComponent;
import com.drpicox.game.games.Game;

import javax.persistence.Entity;

@Entity
public class Named extends EcsComponent {
    private String name;

    public Named(String entityId, Game game, String initialName) {
        super(entityId, game);
        this.name = initialName;
    }

    protected Named() {}

    public String getName() {
        return name;
    }

    public void changeName(String newName) {
        this.name = newName;
    }
}
