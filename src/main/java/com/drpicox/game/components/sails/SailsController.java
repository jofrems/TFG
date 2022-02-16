package com.drpicox.game.components.sails;

import com.drpicox.game.games.Game;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SailsController {

    private final SailsRepository sailsRepository;

    public SailsController(SailsRepository sailsRepository) {
        this.sailsRepository = sailsRepository;
    }

    public void create(String entityId, Game game) {
        var component = new Sail(entityId, game);
        sailsRepository.save(component);
    }

    public Sail orderSail(String entityId, int destinationLocation) {
        var sail = sailsRepository.findById(entityId).get();
        sail.orderSail(destinationLocation);
        sailsRepository.save(sail);

        return sail;
    }
    public Sail orderHalt(String entityId) {
        var sail = sailsRepository.findById(entityId).get();
        sail.orderHalt();
        sailsRepository.save(sail);

        return sail;
    }

    public boolean isSailing(String entityId) {
        var sail = sailsRepository.findById(entityId).get();
        return sail.isDestinationSail();
    }
}
