package com.leovegas.apiwallet.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequest {

    @NotNull
    private Long accountNumber;

    @NotNull
    @Pattern(regexp = "DEBIT|CREDIT", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String transactionType;

    @NotNull
    private Long transactionId;

    @Positive
    @NotNull
    private Long amount;
}
