package com.tfg.game.entities.vertex;

import com.tfg.game.games.Game;
import com.tfg.game.games.GameJoiner;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VertexGameJoiner  implements GameJoiner{
    private final VertexFactory vertexFactory;

    //DELETE IF MAPGAMEJOINER WORKS

    private int xLocations[]     = {0,1,2, 0,1,2,3, 0,1,2,3, 0,1,2,3,4, 0,1,2,3,4, 0,1,2,3,4,5, 0,1,2,3,4,5, 0,1,2,3,4, 0,1,2,3,4, 0,1,2,3, 0,1,2,3,     0,1,2};
    private int yLocations[]     = {0,0,0, 1,1,1,1, 2,2,2,2, 3,3,3,3,3, 4,4,4,4,4, 5,5,5,5,5,5, 6,6,6,6,6,6, 7,7,7,7,7, 8,8,8,8,8, 9,9,9,9, 10,10,10,10, 11,11,11};
    private int neighbour1LocX[] = {3,4,5, 3,4,5,6, 2,3,4,5, 2,3,4,5,6, 1,2,3,4,5, 1,2,3,4,5,6, 0,1,2,3,4,5, 1,2,3,4,5, 0,1,2,3,4, 1,2,3,4, 0,1,2,3,     1,2,3};
    private int neighbour1LocY[] = {0,0,0, 0,0,0,0, 1,1,1,1, 1,1,1,1,1, 2,2,2,2,2, 2,2,2,2,2,2, 3,3,3,3,3,3, 3,3,3,3,3, 4,4,4,4,4, 4,4,4,4, 5,5,5,5,     5,5,5};
    private int neighbour2LocX[] = {4,5,6, 3,4,5,6, 3,3,5,6, 2,3,4,5,6, 2,3,4,5,6, 1,2,3,4,5,6, 1,2,3,4,5,6, 1,2,3,4,5, 1,2,3,4,5, 1,2,3,4, 1,2,3,4,     1,2,3};
    private int neighbour2LocY[] = {0,0,0, 1,1,1,1, 1,1,1,1, 2,2,2,2,2, 2,2,2,2,2, 3,3,3,3,3,3, 3,3,3,3,3,3, 4,4,4,4,4, 4,4,4,4,4, 5,5,5,5, 5,5,5,5,     6,6,6};
    private int neighbour3LocX[] = {3,4,5, 2,3,4,5, 2,3,4,5, 1,2,3,4,5, 1,2,3,4,5, 0,1,2,3,4,5, 0,1,2,3,4,5, 0,1,2,3,4, 0,1,2,3,4, 0,1,2,3, 0,1,2,3,     0,1,2};
    private int neighbour3LocY[] = {1,1,1, 1,1,1,1, 2,2,2,2, 2,2,2,2,2, 3,3,3,3,3, 3,3,3,3,3,3, 4,4,4,4,4,4, 4,4,4,4,4, 5,5,5,5,5, 5,5,5,5, 6,6,6,6,     6,6,6};

    public VertexGameJoiner(VertexFactory vertexFactory) {
        this.vertexFactory = vertexFactory;

    }

    @Override
    public void joinGame(Player owner, Game game) {
      /*  for (int i = 0; i < xLocations.length; i++){
            vertexFactory.buildVertex();
        }*/
    }
}
