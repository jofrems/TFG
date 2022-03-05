package com.tfg.game.components.tileWeight;

import com.tfg.game.ecs.EcsComponent;
import com.tfg.game.games.Game;

import javax.persistence.Entity;

@Entity
public class TileWeight extends EcsComponent{
    int weight;

    public TileWeight(String entityId, Game game, int weight){
        super(entityId, game);

        this.weight = weight;
    }

    protected TileWeight(){
    }

    public int getWeight(){
        return this.weight;
    }
}
