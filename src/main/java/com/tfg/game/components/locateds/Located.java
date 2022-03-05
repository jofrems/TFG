package com.tfg.game.components.locateds;

import com.tfg.game.ecs.EcsComponent;
import com.tfg.game.games.Game;

import javax.persistence.Entity;

@Entity
public class Located extends EcsComponent {
    private int locX;
    private int locY;

    public Located(String entityId, Game game, int locX, int locY ) {
        super(entityId, game);
        this.locX = locX;
        this.locY = locY;
    }

    protected Located() {}

    public int getRow() {
        return this.locX;
    }
    public int getColumn() {return this.locY; }
}
