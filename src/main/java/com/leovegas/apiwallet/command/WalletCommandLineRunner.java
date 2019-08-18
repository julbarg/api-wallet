package com.leovegas.apiwallet.command;

import com.leovegas.apiwallet.domain.TransactionType;
import com.leovegas.apiwallet.entity.Account;
import com.leovegas.apiwallet.entity.Client;
import com.leovegas.apiwallet.entity.Transaction;
import com.leovegas.apiwallet.repository.ClientRepository;
import com.leovegas.apiwallet.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class WalletCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WalletCommandLineRunner.class);

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {
        Account account = Account.builder()
                .accountNumber(1024501252L)
                .balance(250000)
                .build();

        Client client = Client.builder()
                .fistName("Julian")
                .lastName("Barragan")
                .account(account)
                .build();

        account.setClient(client);

        clientRepository.save(client);

        Transaction transaction = Transaction.builder()
                .date(new Date())
                .amount(100L)
                .transactionType(TransactionType.CREDIT)
                .transactionId(7888L)
                .account(account)
                .build();

        transactionRepository.save(transaction);
    }
}
