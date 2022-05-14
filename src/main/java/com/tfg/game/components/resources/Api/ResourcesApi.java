package com.tfg.game.components.resources.Api;


import com.tfg.game.components.owneds.OwnedsController;
import com.tfg.game.components.resources.ResourcesController;
import com.tfg.game.components.typeds.TypedsController;
import com.tfg.game.components.upgradeds.UpgradedsController;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.api.GamesApi;
import com.tfg.game.players.PlayersController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/resources")
public class ResourcesApi {
    private final OwnedsController ownedsController;
    private final PlayersController playersController;
    private final ResourcesController resourcesController;
    private final TypedsController typedsController;
    private final GamesApi gamesApi;

    public ResourcesApi(OwnedsController ownedsController, PlayersController playersController, ResourcesController resourcesController, TypedsController typedsController, GamesApi gamesApi) {
        this.ownedsController = ownedsController;
        this.playersController = playersController;
        this.resourcesController = resourcesController;
        this.typedsController = typedsController;
        this.gamesApi = gamesApi;
    }

    @PostMapping("{entityId}/trade")
    public GameData Trade(@PathVariable String entityId, @RequestParam String token, @RequestBody TradeForm form){
        //var player = playersController.findPlayer(playerName).get();

        //var trade = tradeController.getGame();

        var resources = resourcesController.makeTrade(entityId, form.getGivenResources(), form.getGetResources());

        var game = resourcesController.getGame(entityId);

        return gamesApi.get(game.getGameName(), game.getCreator().getPlayerName(), token);
    }
}
