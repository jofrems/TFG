package com.tfg.game.components.locateds;

import com.tfg.game.ecs.EcsComponent;
import com.tfg.game.games.Game;

import javax.persistence.Entity;

@Entity
public class Located extends EcsComponent {
    private int x;
    private int y;

    public Located(String entityId, Game game, int locX, int locY ) {
        super(entityId, game);
        this.x = locX;
        this.y = locY;
    }

    protected Located() {}

    public int getRow() {
        return this.x;
    }
    public int getColumn() {return this.y; }
}
