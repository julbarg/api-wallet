package com.leovegas.apiwallet;

import com.leovegas.apiwallet.entity.Player;
import com.leovegas.apiwallet.service.PlayerDAOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PlayerDAOServiceCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(PlayerDAOServiceCommandLineRunner.class);

    @Autowired
    PlayerDAOService playerDAOService;

    @Override
    public void run(String... args) throws Exception {
        Player player = new Player("Julian", "Barragan");
        long insert = playerDAOService.insert(player);

        logger.info("New User is create: " + player);
    }
}
