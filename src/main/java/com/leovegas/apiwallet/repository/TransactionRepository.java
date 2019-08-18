package com.leovegas.apiwallet.repository;

import com.leovegas.apiwallet.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Boolean existsByTransactionId(Long transactionId);
}
