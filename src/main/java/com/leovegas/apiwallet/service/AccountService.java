package com.leovegas.apiwallet.service;

import com.leovegas.apiwallet.domain.AccountRequest;
import com.leovegas.apiwallet.domain.AccountResponse;
import com.leovegas.apiwallet.entity.Account;
import com.leovegas.apiwallet.entity.Client;
import com.leovegas.apiwallet.repository.AccountRepository;
import com.leovegas.apiwallet.repository.ClientRepository;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Async
    public CompletableFuture<AccountResponse> createAccount(AccountRequest request) {
        return CompletableFuture.supplyAsync(() ->
                getAccountResponse(request)
        ).exceptionally(throwable -> {
            Throwable cause = ExceptionUtils.getRootCause(throwable);
            throw new CompletionException(cause);
        });
    }

    @Async
    public CompletableFuture<AccountResponse> getAccountDetail(long accountNumber) {
        return CompletableFuture.supplyAsync(() ->
            getAccountResponse(accountRepository.findByAccountNumber(accountNumber))
        ).exceptionally(throwable -> {
            Throwable cause = ExceptionUtils.getRootCause(throwable);
            throw new CompletionException(cause);
        });
    }

    public AccountResponse getAccountResponse (Account account) {
        return AccountResponse.builder()
                .client(account.getClient())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .client(account.getClient())
                .build();
    }

    private AccountResponse getAccountResponse(AccountRequest request) {
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
}
