package com.tfg.game.components.points;

import com.tfg.game.ecs.EcsComponent;
import com.tfg.game.games.Game;

import javax.persistence.Entity;

@Entity
public class Points extends EcsComponent{
    int points;

    public Points(String entityId, Game game){
        super(entityId, game);

        this.points = 1;
    }

    protected Points(){
    }

    public int getType(){
        return this.points;
    }
}