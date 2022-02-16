package com.drpicox.game.components.growingsPopulation;

import com.drpicox.game.ecs.EcsSystem;
import com.drpicox.game.games.Game;
import com.drpicox.game.components.populateds.PopulatedsController;
import org.springframework.stereotype.Component;

@Component
public class Sys900_GrowsPopulation implements EcsSystem {

    private final GrowingsPopulationsRepository growingsPopulationsRepository;
    private final GrowingsPopulationsController growsPopulationController;
    private final PopulatedsController populatedsController;

    public Sys900_GrowsPopulation(GrowingsPopulationsRepository growingsPopulationsRepository, GrowingsPopulationsController growsPopulationController, PopulatedsController populatedsController) {
        this.growingsPopulationsRepository = growingsPopulationsRepository;
        this.growsPopulationController = growsPopulationController;
        this.populatedsController = populatedsController;
    }

    @Override
    public void act(Game game) {
        var cities = growingsPopulationsRepository.findAllByGame(game);
        cities.forEach(growsPopulation -> {
            populatedsController.increasePopulation(growsPopulation.getEntityId(), 1);
        });
    }
}
