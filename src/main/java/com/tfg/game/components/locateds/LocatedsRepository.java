package com.tfg.game.components.locateds;

import com.tfg.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface LocatedsRepository extends JpaRepository<Located, String> {
    List<Located> findAllByGame(Game game);

    List<Located> findAllByGameAndXAndY(Game game, int x, int y);
}
