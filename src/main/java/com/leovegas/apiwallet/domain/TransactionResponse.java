package com.leovegas.apiwallet.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Builder
@Getter
@AllArgsConstructor
public class TransactionResponse {

    private Long transactionId;

    private String transactionType;

    private Long amount;

    private AccountResponse account;

    private Date date;
}
