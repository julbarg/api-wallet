package com.leovegas.apiwallet.service;

import com.leovegas.apiwallet.domain.AccountRequest;
import com.leovegas.apiwallet.domain.AccountResponse;
import com.leovegas.apiwallet.entity.Account;
import com.leovegas.apiwallet.entity.Client;
import com.leovegas.apiwallet.repository.AccountRepository;
import com.leovegas.apiwallet.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    public AccountResponse createAccount(AccountRequest request) {
        Client client = request.getClient();

        clientRepository.save(client);

        Account account = Account.builder()
                .accountNumber(request.getAccountNumber())
                .balance(request.getBalance())
                .client(request.getClient())
                .build();

        accountRepository.save(account);

        return getAccountResponse(account);
    }

    public AccountResponse getAccountDetail(long accountNumber) {
        Account account =  accountRepository.findByAccountNumber(accountNumber);

        return getAccountResponse(account);
    }

    public AccountResponse getAccountResponse (Account account) {
        return AccountResponse.builder()
                .client(account.getClient())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .client(account.getClient())
                .build();
    }
}
