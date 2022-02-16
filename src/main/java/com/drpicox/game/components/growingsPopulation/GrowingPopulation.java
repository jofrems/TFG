package com.drpicox.game.components.growingsPopulation;

import com.drpicox.game.ecs.EcsComponent;
import com.drpicox.game.games.Game;

import javax.persistence.Entity;

@Entity
public class GrowingPopulation extends EcsComponent {
    public GrowingPopulation(String entityId, Game game) {
        super(entityId, game);
    }

    protected GrowingPopulation() {}
}
