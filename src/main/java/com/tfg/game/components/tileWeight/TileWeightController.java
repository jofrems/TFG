package com.tfg.game.components.tileWeight;

import com.tfg.game.components.elements.Elements;
import com.tfg.game.games.Game;
import org.springframework.stereotype.Component;

@Component
public class TileWeightController {
    private final TileWeightRepository tileWeightRepository;
    private final TileWeightEntityDataGenerator tileWeightEntityDataGenerator;


    public TileWeightController(TileWeightRepository tileWeightRepository, TileWeightEntityDataGenerator tileWeightEntityDataGenerator) {
        this.tileWeightRepository = tileWeightRepository;
        this.tileWeightEntityDataGenerator = tileWeightEntityDataGenerator;
    }

    public void create(String entityId, Game game, int weight) {
        var component = new TileWeight(entityId, game, weight);
        tileWeightRepository.save(component);
    }
}
