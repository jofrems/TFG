package com.tfg.game.games.api;

import com.tfg.game.common.api.GlobalRestException;
import com.tfg.game.common.api.SuccessResponse;
import com.tfg.game.ecs.GameData;
import com.tfg.game.ecs.GameDataGenerator;
import com.tfg.game.games.GamesController;
import com.tfg.game.players.PlayersController;
import com.tfg.game.players.api.LoginForm;
import com.tfg.game.players.api.PlayersApi;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/games")
public class GamesApi {
    private final GamesController gamesController;
    private final PlayersController playersController;
    private final PlayersApi playersApi;
    private final GameDataGenerator gameDataGenerator;

    public GamesApi(GamesController gamesController, PlayersController playersController, PlayersApi playersApi, GameDataGenerator gameDataGenerator) {
        this.gamesController = gamesController;
        this.playersController = playersController;
        this.playersApi = playersApi;
        this.gameDataGenerator = gameDataGenerator;
    }

    @PostMapping
    public SuccessResponse createGame(@RequestBody CreateGameForm form) {
        form.verify();

        var gameName = form.getGameName();
        var token = form.getToken();

        var player = playersController.findPlayerByToken(token).orElseThrow();
        var createdGame = gamesController.createGame(gameName, player);
        if (createdGame.isEmpty())
            throw new GlobalRestException("The " + gameName + " game already exists");


        return new SuccessResponse("The " + gameName + " game has been created successfully");
    }

    @PostMapping("/join")
    public SuccessResponse join(@RequestBody JoinGameForm form) {
        var gameName = form.getGameName();
        var creatorName = form.getCreatorName();
        var token = form.getToken();

        var joiningPlayer = playersController.findPlayerByToken(token).orElseThrow();
        var creatorPlayer = playersController.findPlayer(creatorName).orElseThrow();

        var game = gamesController.join(gameName, creatorPlayer, joiningPlayer).orElseThrow(
                () -> new GlobalRestException("You should join games in which are you not joined")
        );

        return new SuccessResponse("You have joined " + game.getGameName() + " game from " + game.getCreator().getPlayerName() + " successfully");
    }

    @PostMapping("/joinNext")
    public GameData joinNext(@RequestBody JoinNextForm form) {
        var playerName = form.getPlayerName();
        var password = form.getPassword();
        var loginResponse = playersApi.login(new LoginForm(playerName, password));
        var token = loginResponse.getToken();

        var gameName = form.getGameName();
        var creatorName = form.getCreatorName();
        join(new JoinGameForm(gameName, creatorName, token));

        return get(gameName, creatorName, token);
    }

    @PostMapping("/byPlayer")
    public ListGamesResponse listGamesByCreator(@RequestBody ListGamesByCreatorForm form) {
        form.verify();

        var playerName = form.getPlayerName();
        var player = playersController.findPlayer(playerName).orElseThrow(() -> new GlobalRestException("The friend name should be an existing player"));

        var result = new ListGamesResponse();
        var games = gamesController.findByCreator(player);
        games.forEach(result::addGame);

        return result;
    }

    @PostMapping("/byJoined")
    public ListGamesResponse listGamesByJoined(@RequestBody ListGamesByJoinedForm form) {
        var token = form.getToken();
        var player = playersController.findPlayerByToken(token).orElseThrow();

        var result = new ListGamesResponse();
        var games = gamesController.findByJoined(player);
        games.forEach(result::addGame);

        return result;
    }

    @GetMapping("/{gameName}/by/{creatorName}")
    public GameData get(@PathVariable String gameName, @PathVariable String creatorName, @RequestParam String token) {
        var playingPlayer = playersController.findPlayerByToken(token).orElseThrow();
        var creatorPlayer = playersController.findPlayer(creatorName).orElseThrow();

        var game = gamesController.findGame(gameName, creatorPlayer).orElseThrow();
        if (!game.isPlayerJoined(playingPlayer))
            throw new GlobalRestException("You should play only games in which you are joined");

        return gameDataGenerator.generate(game, playingPlayer, token);
        // return gameDataGenerator.generateResponse(game, playingPlayer, token);
    }

    @PostMapping("/{gameName}/by/{creatorName}/endRound")
    public GameData endRound(@PathVariable String gameName, @PathVariable String creatorName, @RequestParam String token) {
        var playingPlayer = playersController.findPlayerByToken(token).orElseThrow();
        var creatorPlayer = playersController.findPlayer(creatorName).orElseThrow();
        var game = gamesController.findByCreatorAndGameName(creatorPlayer,gameName);
        var isCurrentPlayerTurn = gamesController.isCurrentPlayerTurn(game, playingPlayer);

        if(isCurrentPlayerTurn)
            gamesController.endRound(gameName, creatorPlayer);

        return get(gameName, creatorName, token);
    }

    @PostMapping("/{gameName}/by/{creatorName}/playerNameTurn")
    public GameData playerNameTurn(@PathVariable String gameName, @PathVariable String creatorName, @RequestParam String token) {
        var creatorPlayer = playersController.findPlayer(creatorName).orElseThrow();
        var game = gamesController.findByCreatorAndGameName(creatorPlayer,gameName);

        gamesController.getPlayerNameTurn(game);

        return get(gameName, creatorName, token);
    }
}
