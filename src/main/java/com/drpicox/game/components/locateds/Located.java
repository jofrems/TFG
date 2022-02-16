package com.drpicox.game.components.locateds;

import com.drpicox.game.ecs.EcsComponent;
import com.drpicox.game.games.Game;

import javax.persistence.Entity;

@Entity
public class Located extends EcsComponent {
    private int location;

    public Located(String entityId, Game game, int initialLocation) {
        super(entityId, game);
        this.location = initialLocation;
    }

    protected Located() {}

    public int getLocation() {
        return location;
    }

    public void moveTo(int destination) {
        location += destination < location ? -1 : +1;
    }
}
