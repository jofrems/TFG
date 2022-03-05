package com.tfg.game.components.tileType;

import com.tfg.game.ecs.EcsComponent;
import com.tfg.game.games.Game;

import javax.persistence.Entity;

@Entity
public class TileType extends EcsComponent{
    int type;

    public TileType(String entityId, Game game, int type){
        super(entityId, game);

        this.type = type;
    }

    protected TileType(){
    }

    public int getType(){
        return this.type;
    }
}