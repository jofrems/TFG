package com.tfg.game.components.neighbours;

import com.tfg.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface NeighboursRepository extends JpaRepository<Neighbours, String> {
    List<Neighbours> findAllByGame(Game game);
}
