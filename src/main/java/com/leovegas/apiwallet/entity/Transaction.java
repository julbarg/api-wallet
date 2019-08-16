package com.leovegas.apiwallet.entity;

import com.leovegas.apiwallet.domain.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue
    private long id;

    private long accountNumber;

    private TransactionType transactionType;

    private Date date;

    private long amount;
}
