package com.tfg.game.components.points;

import com.tfg.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface PointsRepository extends JpaRepository<Points, String> {
    List<Points> findAllByGame(Game game);
}
