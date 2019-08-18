package com.leovegas.apiwallet.command;

import com.leovegas.apiwallet.entity.Account;
import com.leovegas.apiwallet.entity.Player;
import com.leovegas.apiwallet.repository.AccountRepository;
import com.leovegas.apiwallet.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WalletCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WalletCommandLineRunner.class);

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {
        Player player = new Player("Sandra", "Barragan");

        playerRepository.save(player);
        logger.info("New User is create: " + player);

        Optional<Player> playerWithIdOne = playerRepository.findById(1L);
        logger.info("Player is retrieved: " + playerWithIdOne);

        List<Player> players = playerRepository.findAll();
        logger.info("All Players: " + players);

        Account account = Account.builder()
                .accountNumber(1024501252L)
                .playerId(1234)
                .balance(250000)
                .build();

        accountRepository.save(account);
    }
}
