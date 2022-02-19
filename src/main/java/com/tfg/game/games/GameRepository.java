package com.tfg.game.games;

import com.tfg.game.players.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface GameRepository extends JpaRepository<Game, String> {
    List<Game> findByCreator(Player creator);
}
