package com.leovegas.apiwallet.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Transaction request")
public class TransactionRequest {

    @NotNull
    @Pattern(regexp = "DEBIT|CREDIT", flags = Pattern.Flag.CASE_INSENSITIVE)
    @ApiModelProperty(notes = "Type of Transaction.", example = "DEBIT", required = true, position = 1)
    private String transactionType;

    @NotNull
    @ApiModelProperty(notes = "Unique identifier of Transaction.", example = "121233", required = true, position = 2)
    private Long transactionId;

    @Positive
    @NotNull
    @ApiModelProperty(notes = "Amount of transaction.", example = "100", required = true, position = 3)
    private Double amount;
}
