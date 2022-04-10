package com.tfg.game.components.upgradeds;

import com.tfg.game.ecs.EcsComponent;
import com.tfg.game.games.Game;

import javax.persistence.Entity;

@Entity
public class Upgraded extends EcsComponent {
    private boolean upgraded;

    public Upgraded(String entityId, Game game) {
        super(entityId, game);
        this.upgraded = false;
    }

    protected Upgraded() {}

    public boolean isUpgraded() {
        return upgraded;
    }

    public void upgrade(){ this.upgraded = true;}

}
