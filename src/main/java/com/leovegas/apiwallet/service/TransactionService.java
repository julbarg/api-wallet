package com.leovegas.apiwallet.service;

import com.leovegas.apiwallet.domain.TransactionRequest;
import com.leovegas.apiwallet.domain.TransactionType;
import com.leovegas.apiwallet.entity.Account;
import com.leovegas.apiwallet.entity.Transaction;
import com.leovegas.apiwallet.exception.InsufficientFundsException;
import com.leovegas.apiwallet.exception.TransactionIdUniqueException;
import com.leovegas.apiwallet.repository.AccountRepository;
import com.leovegas.apiwallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Transaction retrieveTransaction(long transactionId) {
        Optional<Transaction> result = transactionRepository.findById(transactionId);

        return result.isPresent() ? result.get() : null;
    }

    public Transaction createTransaction(TransactionRequest request) {
        Account account = accountRepository.findByAccountNumber(request.getAccountNumber());
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

    private long getNewBalance(Account account, Long amount, TransactionType transactionType) {
        long result;
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

    private void validateDebitBalance(Account account, Long amount) {
        long newBalance = getNewBalance(account, amount, TransactionType.DEBIT);

        if (newBalance < 0) {
            throw new InsufficientFundsException();
        }
    }

    private void validateUniqueTransactionId(Long transaciontId) {
        if (transactionRepository.existsByTransactionId(transaciontId)) {
            throw new TransactionIdUniqueException();
        }
    }
}
