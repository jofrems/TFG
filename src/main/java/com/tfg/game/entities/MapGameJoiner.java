package com.tfg.game.entities;

import com.tfg.game.entities.tile.TileFactory;
import com.tfg.game.entities.vertex.VertexFactory;
import com.tfg.game.entities.road.RoadFactory;


import com.tfg.game.games.Game;
import com.tfg.game.games.GameJoiner;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

@Component
public class MapGameJoiner implements GameJoiner {

    private final TileFactory tileFactory;
    private final VertexFactory vertexFactory;
    private final RoadFactory roadFactory;

    public static final int SEA = -1;
    public static final int DESSERT = -2;
    public static final int LUMBER = 0;
    public static final int GRAIN = 1;
    public static final int WOOL = 2;
    public static final int BRICK = 3;
    public static final int ORE = 4;

    //TILES
    private final int MAX_TILES = 37;
    private int columnLocations[]= {3,4,5,2,3,4,5,1,2,4,5,1,2,3,4,1,2,3   ,3,4,5,6,2,6,1,6,0,6,0,5,0,4,0,1,2,3,3};
    private int rowLocations[] =   {1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5   ,0,0,0,0,1,1,2,2,3,3,4,4,5,5,6,6,6,6,3};
    private int weights []=        {2,3,3,4,4,5,5,6,6,8,8,9,9,10,10,11,11,12  ,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    private int types []=          {0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,4,4,4, -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2};

    //VERTEX
    private int xLocations[]     = {3,5,7, 2,4,6,8, 2,4,6,8, 1,3,5,7,9, 1,3,5,7,9, 0,2,4,6,8,10, 0,2,4,6,8,10, 1,3,5,7,9, 1,3,5,7,9, 2,4,6,8, 2,4,6,8,     3,5,7};
    //private //int xLocations[]     = {0,1,2, 0,1,2,3, 0,1,2,3, 0,1,2,3,4, 0,1,2,3,4, 0,1,2,3,4,5, 0,1,2,3,4,5, 0,1,2,3,4, 0,1,2,3,4, 0,1,2,3, 0,1,2,3,     0,1,2};
    private int yLocations[]     = {0,0,0, 1,1,1,1, 2,2,2,2, 3,3,3,3,3, 4,4,4,4,4, 5,5,5,5,5,5, 6,6,6,6,6,6, 7,7,7,7,7, 8,8,8,8,8, 9,9,9,9, 10,10,10,10, 11,11,11};
    private int neighbour1LocX[] = {3,4,5, 3,4,5,6, 2,3,4,5, 2,3,4,5,6, 1,2,3,4,5, 1,2,3,4,5,6, 0,1,2,3,4,5, 1,2,3,4,5, 0,1,2,3,4, 1,2,3,4, 0,1,2,3,     1,2,3};
    private int neighbour1LocY[] = {0,0,0, 0,0,0,0, 1,1,1,1, 1,1,1,1,1, 2,2,2,2,2, 2,2,2,2,2,2, 3,3,3,3,3,3, 3,3,3,3,3, 4,4,4,4,4, 4,4,4,4, 5,5,5,5,     5,5,5};
    private int neighbour2LocX[] = {4,5,6, 3,4,5,6, 3,3,5,6, 2,3,4,5,6, 2,3,4,5,6, 1,2,3,4,5,6, 1,2,3,4,5,6, 1,2,3,4,5, 1,2,3,4,5, 1,2,3,4, 1,2,3,4,     1,2,3};
    private int neighbour2LocY[] = {0,0,0, 1,1,1,1, 1,1,1,1, 2,2,2,2,2, 2,2,2,2,2, 3,3,3,3,3,3, 3,3,3,3,3,3, 4,4,4,4,4, 4,4,4,4,4, 5,5,5,5, 5,5,5,5,     6,6,6};
    private int neighbour3LocX[] = {3,4,5, 2,3,4,5, 2,3,4,5, 1,2,3,4,5, 1,2,3,4,5, 0,1,2,3,4,5, 0,1,2,3,4,5, 0,1,2,3,4, 0,1,2,3,4, 0,1,2,3, 0,1,2,3,     0,1,2};
    private int neighbour3LocY[] = {1,1,1, 1,1,1,1, 2,2,2,2, 2,2,2,2,2, 3,3,3,3,3, 3,3,3,3,3,3, 4,4,4,4,4,4, 4,4,4,4,4, 5,5,5,5,5, 5,5,5,5, 6,6,6,6,     6,6,6};

    //ROADS
    //private int xLocationsRoads[] = {0,1,2,3,4,5, 0,1,2,3, 0,1,2,3,4,5,6,7, 0,1,2,3,4, 0,1,2,3,4,5,6,7,8,9, 0,1,2,3,4,5, 0,1,2,3,4,5,6,7,8,9, 0,1,2,3,4, 0,1,2,3,4,5,6,7, 0,1,2,3, 0,1,2,3,4,5};
    private int xLocationsRoads[] = {5,7,9,11,13,15, 4,8,12,16, 3,5,7,9,11,13,15,17, 2,6,10,14,18, 1,3,5,7,9,11,13,15,17,19, 0,4,8,12,16,20, 1,3,5,7,9,11,13,15,17,19, 2,6,10,14,18, 3,5,7,9,11,13,15,17, 4,8,12,16, 5,7,9,11,13,15};
    private int yLocationsRoads[] = {0,0,0,0 ,0 ,0,  1,1,1 ,1,  2,2,2,2,2 ,2 ,2 ,2,  3,3,3 ,3 ,3,  4,4,4,4,4,4 ,4 ,4 ,4 ,4 , 5,5,5,5 ,5 ,5 , 6,6,6,6,6,6 ,6 ,6 ,6 ,6 , 7,7,7 ,7 ,7 , 8,8,8,8,8 ,8 ,8  ,8, 9,9,9 ,9 , 10,10,10,10,10,10};
    public MapGameJoiner(TileFactory tileFactory, VertexFactory vertexFactory, RoadFactory roadFactory) {
        this.tileFactory = tileFactory;
        this.vertexFactory = vertexFactory;
        this.roadFactory = roadFactory;
    }

    @Override
    public void joinGame(Player owner, Game game) {
        //treure map, fer un for aqui q generi tantes tiles com necessiti, aqui portar la llogica dels tipus de tiles weights etc
        for(int i = 0; i < MAX_TILES; i++){
            tileFactory.buildTile(game, owner, types[i], weights[i], rowLocations[i], columnLocations[i]);
        }

       for(int i = 0; i < xLocations.length; i++){
            vertexFactory.buildVertex(game, owner,xLocations[i], yLocations[i], findTileWeightGivenTilePosition(neighbour1LocX[i], neighbour1LocY[i]), findTileTypeGivenTilePosition(neighbour1LocX[i], neighbour1LocY[i]), findTileWeightGivenTilePosition(neighbour2LocX[i], neighbour2LocY[i]), findTileTypeGivenTilePosition(neighbour2LocX[i], neighbour2LocY[i]),findTileWeightGivenTilePosition(neighbour3LocX[i], neighbour3LocY[i]),findTileTypeGivenTilePosition(neighbour3LocX[i], neighbour3LocY[i]));
        }

       for (int i=0; i < xLocationsRoads.length; i++){
           roadFactory.buildRoad(game, xLocationsRoads[i], yLocationsRoads[i]);
       }
    }

    public int findTileTypeGivenTilePosition(int x, int y){
        for (int i = 0; i < columnLocations.length; i++){
            if(x == columnLocations[i]){
                if(y == rowLocations[i])
                    return types[i];
            }
        }
        return -1;
    }

    public int findTileWeightGivenTilePosition(int x, int y){
        for (int i = 0; i < columnLocations.length; i++){
            if(x == columnLocations[i]){
                if(y == rowLocations[i])
                    return weights[i];
            }
        }
        return -1;
    }
}
