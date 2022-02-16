package com.drpicox.game.components.loadables;

import com.drpicox.game.games.Game;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LoadablesController {

    private final LoadablesRepository loadablesRepository;

    public LoadablesController(LoadablesRepository loadablesRepository) {
        this.loadablesRepository = loadablesRepository;
    }

    public void create(String entityId, Game game) {
        var component = new Loadable(entityId, game);
        loadablesRepository.save(component);
    }

    public Loadable orderLoad(String entityId, int loadAmount, String sourceEntityId) {
        var loadable = loadablesRepository.findById(entityId).get();
        loadable.orderLoadUnload(loadAmount, sourceEntityId);
        loadablesRepository.save(loadable);
        return loadable;
    }

    public Loadable orderUnload(String entityId, int loadAmount, String sourceEntityId) {
        var loadable = loadablesRepository.findById(entityId).get();
        loadable.orderLoadUnload(-loadAmount, sourceEntityId);
        loadablesRepository.save(loadable);
        return loadable;
    }
}
