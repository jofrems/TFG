package com.drpicox.game.players.api;

import com.drpicox.game.common.api.GlobalRestException;
import com.drpicox.game.common.api.SuccessResponse;
import com.drpicox.game.players.PlayersController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/players")
public class PlayersApi {

    private final PlayersController playersController;

    public PlayersApi(PlayersController playersController) {
        this.playersController = playersController;
    }

    @PostMapping
    public SuccessResponse signup(@RequestBody SignupForm form) {
        form.verify();

        var playerName = form.getPlayerName();
        var password = form.getPassword();

        playersController.addPlayer(playerName, password);

        return new SuccessResponse("The " + form.getPlayerName() + " player has been added successfully");
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginForm form) {
        var playerName = form.getPlayerName();
        var password = form.getPassword();

        var token = playersController.findPlayerToken(playerName, password)
                .orElseThrow(this::newWrongLoginCredentials);

        var message = "The " + playerName + " player has been logged successfully";

        return new LoginResponse(playerName, token, message);
    }

    private GlobalRestException newWrongLoginCredentials() {
        return new GlobalRestException("The login credentials are wrong");
    }
}
