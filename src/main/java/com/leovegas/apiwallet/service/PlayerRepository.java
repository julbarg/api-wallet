package com.leovegas.apiwallet.service;

import com.leovegas.apiwallet.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
