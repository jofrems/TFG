package com.tfg.game.components.dices;

import com.tfg.game.ecs.EcsComponent;
import com.tfg.game.games.Game;

import javax.persistence.Entity;

import java.util.concurrent.ThreadLocalRandom;

@Entity
public class Dices extends EcsComponent{
    private int dice1;
    private int dice2;
    private boolean isRolled;

    static final int MIN = 1;
    static final int MAX = 6;

    public Dices(String entityId, Game game) {
        super(entityId, game);
        this.dice1 = 0;
        this.dice2 = 0;
        this.isRolled = false;
    }

    protected Dices(){}

    public int getDice1(){ return this.dice1; }

    public int getDice2(){
        return this.dice2;
    }

    public boolean isRolled(){return this.isRolled;}

    public void updateIsRolled(boolean value){
        this.isRolled = value;
    }


    public void rollDices(){
        this.dice1 = ThreadLocalRandom.current().nextInt(MIN, MAX +1);
        this.dice2 = ThreadLocalRandom.current().nextInt(MIN, MAX +1);
    }

    public int getSumDices(){
        return this.dice1 + this.dice2;
    }
}
