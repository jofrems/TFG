package com.drpicox.game.components.sails;

import com.drpicox.game.components.locateds.LocatedsController;
import com.drpicox.game.ecs.EcsSystem;
import com.drpicox.game.games.Game;
import org.springframework.stereotype.Component;

@Component
public class Sys200_Sail implements EcsSystem {

    private final SailsRepository sailsRepository;
    private final LocatedsController locatedsController;

    public Sys200_Sail(SailsRepository sailsRepository, LocatedsController locatedsController) {
        this.sailsRepository = sailsRepository;
        this.locatedsController = locatedsController;
    }

    @Override
    public void act(Game game) {
        var sails = sailsRepository.findAllByGame(game);
        sails.forEach(sail -> {
            var isSail = sail.isDestinationSail();

            if (isSail) actSail(sail);
        });
    }

    private void actSail(Sail sail) {
        var entityId = sail.getEntityId();
        var destination = sail.getDestinationLocation();
        locatedsController.moveTo(entityId, destination);

        var location = locatedsController.getLocation(entityId);
        if (location == destination) {
            sail.halt();
            sailsRepository.save(sail);
        }
    }
}
