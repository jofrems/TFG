package com.tfg.game.components.resources;

import com.tfg.game.ecs.EcsComponent;
import com.tfg.game.games.Game;

import javax.persistence.Entity;

@Entity
public class Resources extends EcsComponent{
    private int brick;
    private int lumber;
    private int wool;
    private int grain;
    private int ore;

    public Resources(String entityId, Game game) {
        super(entityId, game);
        this.brick = 20; //was 4
        this.lumber = 20; //was 4
        this.wool = 4; //was 2
        this.grain = 4; //was 2
        this.ore = 0; //was 0
    }

    protected Resources(){}

    public int getBrick(){
        return this.brick;
    }

    public int getLumber(){
        return this.lumber;
    }

    public int getWool(){
        return this.wool;
    }

    public int getGrain(){return this.grain; }

    public int getOre(){return this.ore; }

    public int incOrDecBrick (int amount, boolean increase){
        if (increase)
            this.brick += amount;
        else
            this.brick -= amount;
        return this.brick;
    }

    public int incOrDecLumber (int amount, boolean increase){
        if (increase)
            this.lumber += amount;
        else
            this.lumber -= amount;
        return this.lumber;
    }

    public int incOrDecWool (int amount, boolean increase){
        if (increase)
            this.wool += amount;
        else
            this.wool -= amount;
        return this.wool;
    }

    public int incOrDecGrain (int amount, boolean increase){
        if (increase)
            this.grain += amount;
        else
            this.grain -= amount;
        return this.grain;
    }

    public int incOrDecOre (int amount, boolean increase){
        if (increase)
            this.ore += amount;
        else
            this.ore -= amount;
        return this.ore;
    }

}
