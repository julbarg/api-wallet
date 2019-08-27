package com.leovegas.apiwallet.service;

import com.leovegas.apiwallet.domain.TransactionRequest;
import com.leovegas.apiwallet.domain.TransactionResponse;
import com.leovegas.apiwallet.domain.TransactionType;
import com.leovegas.apiwallet.entity.Account;
import com.leovegas.apiwallet.entity.Transaction;
import com.leovegas.apiwallet.exception.AccountNotFoundException;
import com.leovegas.apiwallet.exception.InsufficientFundsException;
import com.leovegas.apiwallet.exception.TransactionIdUniqueException;
import com.leovegas.apiwallet.exception.TransactionNotFoundException;
import com.leovegas.apiwallet.repository.AccountRepository;
import com.leovegas.apiwallet.repository.TransactionRepository;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Async
    public CompletableFuture<Transaction> retrieveTransaction(long transactionId) {
        return CompletableFuture.supplyAsync(() ->
            getTransaction(transactionId)
        ).exceptionally(throwable -> {
            Throwable cause = ExceptionUtils.getRootCause(throwable);

            throw new CompletionException(cause);
        });
    }

    @Async
    public CompletableFuture<Set<TransactionResponse>> retrieveHistoryTransaction(long accountNumber) {
        return CompletableFuture.supplyAsync(() ->
                getTransactionResponses(accountNumber)
        ).exceptionally(throwable -> {
            Throwable cause = ExceptionUtils.getRootCause(throwable);

            throw new CompletionException(cause);
        });
    }

    @Async
    public CompletableFuture<Transaction> createTransaction(long accountNumber, TransactionRequest request) {
        return CompletableFuture.supplyAsync(() ->
            getTransaction(accountNumber, request)
        ).exceptionally(throwable -> {
            Throwable cause = ExceptionUtils.getRootCause(throwable);

            throw new CompletionException(cause);
        });
    }

    private Transaction getTransaction(long accountNumber, TransactionRequest request) {
        Account account = accountRepository.findByAccountNumber(accountNumber);

        if(account == null) {
            throw new AccountNotFoundException();
        }

        TransactionType transactionType = TransactionType.valueOf(request.getTransactionType().toUpperCase());

        validateUniqueTransactionId(request.getTransactionId());

        if (TransactionType.DEBIT.equals(transactionType)) {
            validateDebitBalance(account, request.getAmount());
        }

        Transaction transaction = Transaction.builder()
                .transactionType(transactionType)
                .account(account)
                .amount(request.getAmount())
                .transactionId(request.getTransactionId())
                .date(new Date())
                .build();

        transactionRepository.save(transaction);

        account.setBalance(getNewBalance(account, request.getAmount(), transactionType));
        accountRepository.save(account);

        return transaction;
    }

    private double getNewBalance(Account account, Double amount, TransactionType transactionType) {
        double result;

        switch (transactionType) {
            case DEBIT:
                result = account.getBalance() - amount;
                break;
            case CREDIT:
                result = account.getBalance() + amount;
                break;
            default:
                throw new RuntimeException("Transaction Type Invalid");
        }

        return result;
    }


    private void validateDebitBalance(Account account, Double amount) {
        double newBalance = getNewBalance(account, amount, TransactionType.DEBIT);

        if (newBalance < 0) {
            throw new InsufficientFundsException();
        }
    }

    private void validateUniqueTransactionId(Long transaciontId) {
        if (transactionRepository.existsByTransactionId(transaciontId)) {
            throw new TransactionIdUniqueException();
        }
    }

    private Set<TransactionResponse> getTransactionResponses(long accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException();
        }

        return transactionRepository.findByAccount(account).stream().map(
                transaction ->
                        TransactionResponse.builder()
                                .date(transaction.getDate())
                                .transactionId(transaction.getTransactionId())
                                .transactionType(transaction.getTransactionType().name())
                                .amount(transaction.getAmount())
                                .build()
        ).collect(Collectors.toSet());
    }

    private Transaction getTransaction(long transactionId) {
        Transaction transaction = transactionRepository.findByTransactionId(transactionId);

        if (transaction == null) {
            throw new TransactionNotFoundException("Transaction Not Found id: " + transactionId);
        }

        return transaction;
    }
}
