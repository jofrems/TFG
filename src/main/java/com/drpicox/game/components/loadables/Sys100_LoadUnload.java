package com.drpicox.game.components.loadables;

import com.drpicox.game.ecs.EcsSystem;
import com.drpicox.game.games.Game;
import com.drpicox.game.components.populateds.PopulatedsController;
import org.springframework.stereotype.Component;

@Component
public class Sys100_LoadUnload implements EcsSystem {

    private final PopulatedsController populatedsController;
    private final LoadablesRepository loadablesRepository;

    public Sys100_LoadUnload(PopulatedsController populatedsController, LoadablesRepository loadablesRepository) {
        this.populatedsController = populatedsController;
        this.loadablesRepository = loadablesRepository;
    }

    @Override
    public void act(Game game) {
        var loadables = loadablesRepository.findAllByGame(game);
        loadables.forEach(loadable -> {
            var entityId = loadable.getEntityId();
            var loadUnloadAmount = loadable.getLoadUnloadAmount();
            if (loadUnloadAmount == 0) return;

            var targetId = loadable.getSourceEntityId();
            var dockPopulation = populatedsController.getPopulation(targetId);

            loadUnloadAmount = Math.min(loadUnloadAmount, dockPopulation);
            var unfit = populatedsController.increasePopulation(entityId, loadUnloadAmount);
            loadable.clearLoadUnloadOrder();
            loadablesRepository.save(loadable);

            var delta = -(loadUnloadAmount - unfit);
            populatedsController.increasePopulation(targetId, delta);
        });

    }
}
