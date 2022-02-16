package com.drpicox.game.games;

import com.drpicox.game.players.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface GameRepository extends JpaRepository<Game, String> {
    List<Game> findByCreator(Player creator);
}
