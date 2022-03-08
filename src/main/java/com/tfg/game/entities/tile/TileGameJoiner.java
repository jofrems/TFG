package com.tfg.game.entities.tile;

import com.tfg.game.games.Game;
import com.tfg.game.games.GameJoiner;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TileGameJoiner implements GameJoiner {

    private final TileFactory tileFactory;

    public static final int SEA = -1;
    public static final int DESSERT = -2;
    public static final int LUMBER = 0;
    public static final int GRAIN = 1;
    public static final int WOOL = 2;
    public static final int BRICK = 3;
    public static final int ORE = 4;

    private final int MAX_TILES = 37;
    private int columnLocations[]= {3,4,5,2,3,4,5,1,2,4,5,1,2,3,4,1,2,3   ,3,4,5,6,2,6,1,6,0,6,0,5,0,4,0,1,2,3,3};
    private int rowLocations[] =   {1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5   ,0,0,0,0,1,1,2,2,3,3,4,4,5,5,6,6,6,6,3};
    private int weights []= {2,3,3,4,4,5,5,6,6,8,8,9,9,10,10,11,11,12};
    private int types []= {0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,4,4,4, -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2};

    public TileGameJoiner(TileFactory tileFactory) {
        this.tileFactory = tileFactory;

    }
    //posar aqui el que tinc a tiles component
    @Override
    public void joinGame(Player owner, Game game) {
        //treure map, fer un for aqui q generi tantes tiles com necessiti, aqui portar la llogica dels tipus de tiles weights etc
        for(int i = 0; i < MAX_TILES; i++){
            if(i < 18)
                tileFactory.buildTile(game, owner, types[i], weights[i], rowLocations[i], columnLocations[i]);
            else
                tileFactory.buildTile(game, owner, types[i], -1, rowLocations[i], columnLocations[i]);

        }
    }
}
