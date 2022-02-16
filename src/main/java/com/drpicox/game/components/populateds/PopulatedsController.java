package com.drpicox.game.components.populateds;

import com.drpicox.game.games.Game;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PopulatedsController {

    private final PopulatedsRepository populatedsRepository;

    public PopulatedsController(PopulatedsRepository populatedsRepository) {
        this.populatedsRepository = populatedsRepository;
    }

    public void create(String entityId, Game game, int initialPopulation) {
        var component = new Populated(entityId, game, initialPopulation);
        populatedsRepository.save(component);
    }

    public int increasePopulation(String entityId, int increment) {
        var populated = populatedsRepository.findById(entityId).get();
        var result = populated.increasePopulation(increment);
        populatedsRepository.save(populated);
        return result;
    }

    public int getPopulation(String entityId) {
        var populated = populatedsRepository.findById(entityId).get();
        return populated.getPopulation();
    }
}
