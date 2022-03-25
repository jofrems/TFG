package com.tfg.game.components.owneds.Api;


import com.tfg.game.components.owneds.OwnedsController;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.api.GamesApi;
import com.tfg.game.players.PlayersController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/owned")
public class OwnedApi {
    private final OwnedsController ownedsController;
    private final PlayersController playersController;
    private final GamesApi gamesApi;

    public OwnedApi(OwnedsController ownedsController, PlayersController playersController, GamesApi gamesApi) {
        this.ownedsController = ownedsController;
        this.playersController = playersController;
        this.gamesApi = gamesApi;
    }

    @PostMapping("{entityId}/{playerName}/own")
    public GameData Own(@PathVariable String entityId, @PathVariable String playerName, @RequestParam String token){
        var player = playersController.findPlayer(playerName).get();
        var owned = ownedsController.own(entityId, player);
        var game = owned.getGame();

        return gamesApi.get(game.getGameName(), game.getCreator().getPlayerName(), token);
    }
}
