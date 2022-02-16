package com.drpicox.game.components.loadables.api;

import com.drpicox.game.ecs.GameData;
import com.drpicox.game.games.api.GamesApi;
import com.drpicox.game.components.loadables.LoadablesController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loadables")
public class LoadablesApi {

    private final LoadablesController loadablesController;
    private final GamesApi gamesApi;

    public LoadablesApi(LoadablesController loadablesController, GamesApi gamesApi) {
        this.loadablesController = loadablesController;
        this.gamesApi = gamesApi;
    }

    @PostMapping("/{entityId}/load")
    public GameData load(@PathVariable String entityId, @RequestParam String token, @RequestBody LoadableForm form) {
        var loadable = loadablesController.orderLoad(entityId, form.getLoadUnloadAmount(), form.getSourceEntityId());

        var game = loadable.getGame();
        return gamesApi.get(game.getGameName(), game.getCreator().getPlayerName(), token);
    }

    @PostMapping("/{entityId}/unload")
    public GameData unload(@PathVariable String entityId, @RequestParam String token, @RequestBody LoadableForm form) {
        var loadable = loadablesController.orderUnload(entityId, form.getLoadUnloadAmount(), form.getSourceEntityId());

        var game = loadable.getGame();
        return gamesApi.get(game.getGameName(), game.getCreator().getPlayerName(), token);
    }
}
