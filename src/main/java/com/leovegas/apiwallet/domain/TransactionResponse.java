package com.leovegas.apiwallet.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Builder
@Getter
@AllArgsConstructor
@JsonFilter("TransactionFilter")
public class TransactionResponse {

    private Long transactionId;

    private String transactionType;

    private Double amount;

    private AccountResponse account;

    private Date date;
}
