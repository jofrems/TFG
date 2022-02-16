package com.drpicox.game.ecs;

import com.drpicox.game.games.Game;

import javax.persistence.*;

@MappedSuperclass
public class EcsComponent {
    @Id private String entityId;
    @ManyToOne private Game game;

    public EcsComponent(String entityId, Game game) {
        this.entityId = entityId;
        this.game = game;
    }

    protected EcsComponent() {}

    public String getEntityId() {
        return entityId;
    }

    public Game getGame() {
        return game;
    }

}
