package com.tfg.game.components.trades;


import com.tfg.game.ecs.EcsComponent;
import com.tfg.game.games.Game;

import javax.persistence.Entity;

@Entity
public class Trade extends EcsComponent{
    private String givenResources;
    private String returnedResources;

    public Trade(String entityId, Game game) {
        super(entityId, game);
        this.givenResources = "";
        this.returnedResources = "";
    }


    public String getReturnedResources() {
        return returnedResources;
    }

    public String getGivenResources() {
        return givenResources;
    }
}
