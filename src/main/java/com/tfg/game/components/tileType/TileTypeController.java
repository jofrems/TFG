package com.tfg.game.components.tileType;

import com.tfg.game.games.Game;
import org.springframework.stereotype.Component;

@Component
public class TileTypeController {
    private final TileTypeRepository tileTypeRepository;
    private final TileTypeEntityDataGenerator tileTypeEntityDataGenerator;


    public TileTypeController(TileTypeRepository tileTypeRepository, TileTypeEntityDataGenerator tileTypeEntityDataGenerator) {
        this.tileTypeRepository = tileTypeRepository;
        this.tileTypeEntityDataGenerator = tileTypeEntityDataGenerator;
    }

    public void create(String entityId, Game game, int type) {
        var component = new TileType(entityId, game, type);
        tileTypeRepository.save(component);
    }
}
