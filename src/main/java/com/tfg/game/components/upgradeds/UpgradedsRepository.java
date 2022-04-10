package com.tfg.game.components.upgradeds;

import com.tfg.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface UpgradedsRepository extends JpaRepository<Upgraded, String> {
    List<Upgraded> findAllByGame(Game game);
}
