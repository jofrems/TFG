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

    public Points(String entityId, Game game, int value){
        super(entityId, game);

        this.points = value;
    }

    protected Points(){
    }

    public int getPoints(){
        return this.points;
    }

    public void updatePoints(int points){
        this.points = points;
    }
}