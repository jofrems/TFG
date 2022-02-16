package com.drpicox.game.players;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface PlayerRepository extends JpaRepository<Player, String> {

    Optional<Player> findByToken(String token);
}
