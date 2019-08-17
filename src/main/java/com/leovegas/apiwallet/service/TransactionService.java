package com.leovegas.apiwallet.service;

import com.leovegas.apiwallet.domain.TransactionRequest;
import com.leovegas.apiwallet.domain.TransactionType;
import com.leovegas.apiwallet.entity.Transaction;
import com.leovegas.apiwallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction retrieveTransaction(long transactionId) {
        Optional<Transaction> result = transactionRepository.findById(transactionId);

        return result.isPresent() ? result.get() : null;
    }

    public Transaction createTransaction(TransactionRequest request) {
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.valueOf(request.getTransactionType().toUpperCase()))
                .accountNumber(request.getAccountNumber())
                .amount(request.getAmount())
                .transactionId(request.getTransactionId())
                .date(new Date())
                .build();

        return transactionRepository.save(transaction);
    }
}
