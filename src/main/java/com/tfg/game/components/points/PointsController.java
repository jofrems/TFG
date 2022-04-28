package com.tfg.game.components.points;

import com.tfg.game.games.Game;
import org.springframework.stereotype.Component;

@Component
public class PointsController {
    private final PointsRepository pointsRepository;
    private final PointsEntityDataGenerator pointsEntityDataGenerator;


    public PointsController(PointsRepository pointsRepository, PointsEntityDataGenerator pointsEntityDataGenerator) {
        this.pointsRepository = pointsRepository;
        this.pointsEntityDataGenerator = pointsEntityDataGenerator;
    }

    public void create(String entityId, Game game) {
        var component = new Points(entityId, game);
        pointsRepository.save(component);
    }
}
