package com.leovegas.apiwallet.service;

import com.leovegas.apiwallet.domain.AccountResponse;
import com.leovegas.apiwallet.entity.Account;
import com.leovegas.apiwallet.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountResponse getAccountDetail(long accountNumber) {
        Account account =  accountRepository.findByAccountNumber(accountNumber);

        return AccountResponse.builder()
                .firsName("Julian")
                .lastName("Barragan")
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .playerId(account.getPlayerId())
                .build();
    }
}
