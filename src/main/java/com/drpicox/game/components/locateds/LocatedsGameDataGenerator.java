package com.drpicox.game.components.locateds;

import com.drpicox.game.components.docks.DocksController;
import com.drpicox.game.components.owneds.OwnedsController;
import com.drpicox.game.ecs.EcsComponent;
import com.drpicox.game.ecs.EntityReachableDataGenerator;
import com.drpicox.game.ecs.GameData;
import com.drpicox.game.ecs.GameReachableDataGenerator;
import com.drpicox.game.games.Game;
import com.drpicox.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Component
public class LocatedsGameDataGenerator implements GameReachableDataGenerator {

    private final LocatedsRepository locatedsRepository;
    private final OwnedsController ownedsController;
    private final DocksController docksController;
    private final List<EntityReachableDataGenerator> entityReachableDataGenerators;

    public LocatedsGameDataGenerator(LocatedsRepository locatedsRepository, OwnedsController ownedsController, DocksController docksController, List<EntityReachableDataGenerator> entityReachableDataGenerators) {
        this.locatedsRepository = locatedsRepository;
        this.ownedsController = ownedsController;
        this.docksController = docksController;
        this.entityReachableDataGenerators = entityReachableDataGenerators;
    }

    @Override
    public void generateReachableData(GameData data, Game game, Player playingPlayer) {
        var owneds = ownedsController.findAllByGameAndOwner(game, playingPlayer);
        var ownedEntityIds = getIds(owneds);

        var docks = docksController.findAllByGame(game);
        var dockEntityIds = getIds(docks);
        var docksLocateds = locatedsRepository.findAllById(dockEntityIds);
        var docksLocations = new TreeSet<>(getLocations(docksLocateds));

        var ownerLocateds = locatedsRepository.findAllById(ownedEntityIds);
        var ownerLocations = getLocations(ownerLocateds);

        var reachableLocations = new TreeSet<Integer>();
        reachableLocations.addAll(ownerLocations);
        ownerLocations.forEach(location -> {
            if (!docksLocations.contains(location - 1)) reachableLocations.add(location - 1);
            if (!docksLocations.contains(location + 1)) reachableLocations.add(location + 1);
        });

        var reachableLocateds = locatedsRepository.findAllByLocationIn(reachableLocations);
        var reachableEntityIds = getIds(reachableLocateds);
        entityReachableDataGenerators.forEach(g -> g.generateReachableData(data, game, playingPlayer, reachableEntityIds));
    }

    private static List<Integer> getLocations(List<Located> docksLocateds) {
        return docksLocateds.stream().map(c -> c.getLocation()).collect(Collectors.toList());
    }

    private static <T extends EcsComponent> List<String> getIds(List<T> components) {
        return components.stream().map(c -> c.getEntityId()).collect(Collectors.toList());
    }
}
