package com.leovegas.apiwallet.command;

import com.leovegas.apiwallet.entity.Account;
import com.leovegas.apiwallet.entity.Client;
import com.leovegas.apiwallet.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class WalletCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WalletCommandLineRunner.class);

    @Autowired
    ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {
        Long[] accountNumbers = {1024501252L, 8795414L, 987451L, 854745L, 987454L};
        Double[] balances = {0.0, 250.0, 799.0, 4515.8, 74554.7};
        String[] firstNames = {"Julian", "Bob", "Tony", "Bruce", "Bob"};
        String[] lastNames = {"Barragan", "Marley", "Stark", "Wayne", "Dylan"};

        for (int i = 0; i < 5; i++) {
            Account account = Account.builder()
                    .accountNumber(accountNumbers[i])
                    .balance(balances[i])
                    .build();

            Client client = Client.builder()
                    .fistName(firstNames[i])
                    .lastName(lastNames[i])
                    .account(account)
                    .build();

            account.setClient(client);

            clientRepository.save(client);
        }
    }
}
