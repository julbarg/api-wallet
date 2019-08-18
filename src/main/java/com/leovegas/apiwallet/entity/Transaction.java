package com.leovegas.apiwallet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leovegas.apiwallet.domain.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false, unique = true)
    private Long transactionId;

    @Column(nullable = false)
    private Long amount;
}
