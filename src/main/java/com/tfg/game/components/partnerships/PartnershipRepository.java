package com.tfg.game.components.partnerships;

import com.tfg.game.components.neighbours.Neighbours;
import com.tfg.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface PartnershipRepository extends JpaRepository<Partnership, String> {
    List<Partnership> findAllByGame(Game game);
}
