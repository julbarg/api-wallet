package com.leovegas.apiwallet.repository;

import com.leovegas.apiwallet.entity.Account;
import com.leovegas.apiwallet.entity.Transaction;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Boolean existsByTransactionId(Long transactionId);

    List<Transaction> findByAccount(Account account);

    Transaction findByTransactionId(long transactionId);
}
