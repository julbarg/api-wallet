package com.leovegas.apiwallet.repository;

import com.leovegas.apiwallet.entity.Account;
import com.leovegas.apiwallet.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Boolean existsByTransactionId(Long transactionId);

    Transaction findByTransactionId(long transactionId);

    Set<Transaction> findByAccount(Account account);
}
