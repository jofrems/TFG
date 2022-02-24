package com.tfg.game.components.elements;

import com.tfg.game.ecs.EcsComponent;
import com.tfg.game.games.Game;

import javax.persistence.Entity;

@Entity
public class Elements extends EcsComponent{
    private int roads;
    private int towns;
    private int cities;

    public Elements(String entityId, Game game) {
        super(entityId, game);
        this.roads = 15;
        this.towns = 5;
        this.cities = 4;
    }

    protected Elements(){}

    public int getRoads(){
        return this.roads;
    }

    public int getTowns(){
        return this.towns;
    }

    public int getCities(){
        return this.cities;
    }

    public int decreaseRoads(){
        this.roads--;
        return this.roads;
    }

    public int decreaseTowns(){
        this.towns--;
        return this.towns;
    }

    public int decreaseCities(){
        this.cities--;
        return this.cities;
    }
}
