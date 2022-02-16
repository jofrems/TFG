package com.drpicox.game.components.populateds;

import com.drpicox.game.ecs.EcsComponent;
import com.drpicox.game.games.Game;

import javax.persistence.Entity;

@Entity
public class Populated extends EcsComponent {
    public static final int MAX_POPULATION = 20;
    private int population;

    public Populated(String entityId, Game game, int initialPopulation) {
        super(entityId, game);
        this.population = initialPopulation;
    }

    protected Populated() {}

    public int getPopulation() {
        return population;
    }

    int increasePopulation(int increment) {
        var prevPropulation = this.population;
        this.population = Math.min(MAX_POPULATION, Math.max(0, population + increment));
        int unfit = prevPropulation + increment - this.population;
        return unfit;
    }
}
