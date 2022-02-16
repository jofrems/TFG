package com.drpicox.game.components.growingsPopulation;

import com.drpicox.game.games.Game;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GrowingsPopulationsController {

    private final GrowingsPopulationsRepository growingsPopulationsRepository;

    public GrowingsPopulationsController(GrowingsPopulationsRepository growingsPopulationsRepository) {
        this.growingsPopulationsRepository = growingsPopulationsRepository;
    }

    public GrowingPopulation create(String entityId, Game game) {
        var component = new GrowingPopulation(entityId, game);
        growingsPopulationsRepository.save(component);
        return component;
    }

}
