package com.leovegas.apiwallet.repository;

import com.leovegas.apiwallet.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
