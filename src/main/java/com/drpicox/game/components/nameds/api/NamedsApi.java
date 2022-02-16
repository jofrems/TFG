package com.drpicox.game.components.nameds.api;

import com.drpicox.game.components.nameds.NamedsController;
import com.drpicox.game.ecs.GameData;
import com.drpicox.game.games.api.GamesApi;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/nameds")
public class NamedsApi {

    private final NamedsController namedsController;
    private final GamesApi gamesApi;

    public NamedsApi(NamedsController namedsController, GamesApi gamesApi) {
        this.namedsController = namedsController;
        this.gamesApi = gamesApi;
    }

    @PostMapping("/{entityId}/name")
    public GameData changedName(@PathVariable String entityId, @RequestParam String token, @RequestBody NewNamedNameForm form) {
        var named = namedsController.changeName(entityId, form.getNewName());


        var game = named.getGame();
        return gamesApi.get(game.getGameName(), game.getCreator().getPlayerName(), token);
    }
}
