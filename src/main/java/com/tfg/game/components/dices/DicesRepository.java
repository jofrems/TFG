package com.tfg.game.components.dices;

import com.tfg.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface DicesRepository extends JpaRepository<Dices, String> {
    List<Dices> findAllByGame(Game game);
}
