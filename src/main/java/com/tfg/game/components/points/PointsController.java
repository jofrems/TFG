package com.tfg.game.components.points;

import com.tfg.game.components.upgradeds.Upgraded;
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

    public void create(String entityId, Game game, int value) {
        var component = new Points(entityId, game, value);
        pointsRepository.save(component);
    }

    public Points updatePoints(String entityId, int value) {
        var points = pointsRepository.findById(entityId).get();
        points.updatePoints(value);
        pointsRepository.save(points);


        return points;
    }

    public Points updatePoints(String entityId) {
        var points = pointsRepository.findById(entityId).get();
        points.updatePoints(points.getPoints() +1);
        pointsRepository.save(points);


        return points;
    }
}
