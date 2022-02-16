package com.drpicox.game.components.owneds;

import com.drpicox.game.ecs.EcsComponent;
import com.drpicox.game.games.Game;
import com.drpicox.game.players.Player;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Owned extends EcsComponent {
    @ManyToOne
    private Player owner;

    public Owned(String entityId, Game game, Player owner) {
        super(entityId, game);
        this.owner = owner;
    }

    protected Owned() {}

    public Player getOwner() {
        return owner;
    }
}
