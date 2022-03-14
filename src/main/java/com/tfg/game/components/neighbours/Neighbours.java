package com.tfg.game.components.neighbours;

import com.tfg.game.ecs.EcsComponent;
import com.tfg.game.games.Game;

import javax.persistence.Entity;

@Entity
public class Neighbours extends EcsComponent{
    int nWeight;
    int nType;

    int nWeight2;
    int nType2;

    int nWeight3;
    int nType3;

    public Neighbours(String entityId, Game game, int nWeight, int nType, int nWeight2, int nType2, int nWeight3, int nType3){
        super(entityId, game);
        this.nWeight = nWeight;
        this.nType = nType;
        this.nWeight2 = nWeight2;
        this.nType2 = nType2;
        this.nWeight3 = nWeight3;
        this.nType3 = nType3;
    }

    protected Neighbours(){
    }

    public int getNeighbourWeight(){
        return nWeight;
    }
    public int getNeighbour2Weight(){
        return nWeight2;
    }
    public int getNeighbour3Weight(){
        return nWeight3;
    }

    public int getNeighbourType(){ return nType; }
    public int getNeighbour2Type(){ return nType2; }
    public int getNeighbour3Type(){ return nType3; }
}