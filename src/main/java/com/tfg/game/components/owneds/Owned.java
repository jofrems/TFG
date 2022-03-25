package com.tfg.game.components.owneds;

import com.tfg.game.ecs.EcsComponent;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;

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

    public Owned(String entityId, Game game) {
        super(entityId, game);
        this.owner = null;
    }

    protected Owned() {}

    public Player getOwner() {
        return owner;
    }

    public void own(Player player) {
        this.owner = player;
    }


}
