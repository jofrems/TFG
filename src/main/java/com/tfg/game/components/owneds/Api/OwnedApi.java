package com.tfg.game.components.owneds.Api;


import com.tfg.game.components.owneds.OwnedsController;
import com.tfg.game.components.resources.ResourcesController;
import com.tfg.game.components.typeds.TypedsController;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.api.GamesApi;
import com.tfg.game.players.PlayersController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/owned")
public class OwnedApi {
    private final OwnedsController ownedsController;
    private final PlayersController playersController;
    private final ResourcesController resourcesController;
    private final TypedsController typedsController;
    private final GamesApi gamesApi;

    public OwnedApi(OwnedsController ownedsController, PlayersController playersController, ResourcesController resourcesController, TypedsController typedsController, GamesApi gamesApi) {
        this.ownedsController = ownedsController;
        this.playersController = playersController;
        this.resourcesController = resourcesController;
        this.typedsController = typedsController;
        this.gamesApi = gamesApi;
    }

    @PostMapping("{entityId}/{playerName}/own")
    public GameData Own(@PathVariable String entityId, @PathVariable String playerName, @RequestParam String token){
        var player = playersController.findPlayer(playerName).get();
        var ownedTest = ownedsController.own(entityId);

        var inventoryId = ownedsController.findAllByGameAndOwner(ownedTest.getGame(), player).stream()
                    .filter(c -> typedsController.isTyped(c.getEntityId(), "inventory")).findFirst().get().getEntityId();

        var isVertex = typedsController.isTyped(entityId,"vertex");

        boolean resources;
        if(isVertex)
            resources = resourcesController.ownTown(inventoryId);
        else
            resources = resourcesController.ownRoad(inventoryId);

        if(resources) {
            var owned = ownedsController.own(entityId, player);
            var game = owned.getGame();

            return gamesApi.get(game.getGameName(), game.getCreator().getPlayerName(), token);
        }

        var owned = ownedsController.own(entityId);
        var game = owned.getGame();

        return gamesApi.get(game.getGameName(), game.getCreator().getPlayerName(), token);
    }
}
