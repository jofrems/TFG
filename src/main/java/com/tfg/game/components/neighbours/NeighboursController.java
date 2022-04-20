package com.tfg.game.components.neighbours;

import com.tfg.game.games.Game;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NeighboursController {
    private final NeighboursRepository neighboursRepository;
    private final NeighboursEntityDataGenerator tileTypeEntityDataGenerator;


    public NeighboursController(NeighboursRepository tileTypeRepository, NeighboursEntityDataGenerator tileTypeEntityDataGenerator) {
        this.neighboursRepository = tileTypeRepository;
        this.tileTypeEntityDataGenerator = tileTypeEntityDataGenerator;
    }

    public void create(String entityId, Game game, int nWeight, int nType, int nWeight2, int nType2, int nWeight3, int nType3) {
        var component = new Neighbours(entityId, game, nWeight, nType, nWeight2, nType2, nWeight3, nType3);
        neighboursRepository.save(component);
    }

    public List<Neighbours> findAllByGame(Game game){
       return neighboursRepository.findAllByGame(game);
    }
}
