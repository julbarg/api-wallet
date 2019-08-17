package com.leovegas.apiwallet.entity;

import com.leovegas.apiwallet.domain.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue
    private long id;

    private long accountNumber;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Date date;

    private long transactionId;

    private long amount;
}
