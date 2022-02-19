package com.tfg.game.players;

import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class PlayersController {
    private PlayerRepository playerRepository;

    public PlayersController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void addPlayer(String playerName, String password) {
        var player = new Player(playerName, password);
        playerRepository.save(player);
    }

    public Optional<Player> findPlayer(String playerName) {
        return playerRepository.findById(playerName);
    }

    public Optional<String> findPlayerToken(String playerName, String password) {
        return findPlayer(playerName)
                .filter(playerFound -> playerFound.isPasswordOk(password))
                .map(playerFound -> playerFound.getToken());
    }

    public Optional<Player> findPlayerByToken(String token) {
        return playerRepository.findByToken(token);
    }
}
