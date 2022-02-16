package com.drpicox.game.testSteps.sails;

import com.drpicox.game.components.locateds.LocatedsController;
import com.drpicox.game.components.nameds.NamedsController;
import com.drpicox.game.components.owneds.OwnedsController;
import com.drpicox.game.components.sails.SailsController;
import com.drpicox.game.components.sails.SailsEntityDataGenerator;
import com.drpicox.game.components.typeds.TypedsController;
import com.drpicox.game.ecs.EcsComponent;
import com.drpicox.game.games.GamesController;
import com.drpicox.game.games.api.GamesApi;
import com.drpicox.game.players.PlayersController;
import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.game.GameTestView;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.truth.Truth.assertThat;

@Component
public class LetMoveTheirShipAndWaitToArriveAtLocationStep extends AbstractPostLineStep {

    private final GameTestView gameTestView;
    private final PlayersController playersController;
    private final GamesController gamesController;
    private final OwnedsController ownedsController;
    private final TypedsController typedsController;
    private final NamedsController namedsController;
    private final SailsController sailsController;

    public LetMoveTheirShipAndWaitToArriveAtLocationStep(GameTestView gameTestView, PlayersController playersController, GamesController gamesController, OwnedsController ownedsController, TypedsController typedsController, NamedsController namedsController, SailsController sailsController) {
        this.gameTestView = gameTestView;
        this.playersController = playersController;
        this.gamesController = gamesController;
        this.ownedsController = ownedsController;
        this.typedsController = typedsController;
        this.namedsController = namedsController;
        this.sailsController = sailsController;
    }

    @Override
    protected String getRegex() {
        return "Let \"([^\"]+)\" move their ([a-z]+) \"([^\"]+)\" and wait to arrive at location (\\d+)";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var ownerName = match[1];
        var type = match[2];
        var name = match[3];
        var destination = Integer.parseInt(match[4]);

        var gameResponse = gameTestView.getGame();
        String gameName = gameResponse.getGameName();
        var creator = playersController.findPlayer(gameResponse.getCreatorName()).get();
        var game = gamesController.findGame(gameName, creator).get();

        var owner = playersController.findPlayer(ownerName).get();
        var owneds = getIds(ownedsController.findAllByGameAndOwner(game, owner));
        var types = getIds(typedsController.findAllByGameAndType(game, type));
        var nameds = getIds(namedsController.findAllByGameAndName(game, name));

        var intersection = owneds.stream()
                .filter(types::contains)
                .filter(nameds::contains)
                .collect(Collectors.toSet());
        assertThat(intersection).hasSize(1);

        var entityId = intersection.stream().findAny().get();
        sailsController.orderSail(entityId, destination);
        while (sailsController.isSailing(entityId))
            gamesController.endRound(gameName, creator);

        gameTestView.submitRefresh();
    }

    private <T extends EcsComponent> List<String> getIds(List<T> components) {
        return components.stream().map(c -> c.getEntityId()).collect(Collectors.toList());
    }
}
