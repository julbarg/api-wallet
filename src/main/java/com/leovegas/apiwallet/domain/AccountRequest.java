package com.leovegas.apiwallet.domain;

import com.leovegas.apiwallet.entity.Client;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Account request")
public class AccountRequest {

    @NotNull
    @ApiModelProperty(notes = "Account number.", example = "1025547", required = true, position = 0)
    private Long accountNumber;

    @NotNull
    @ApiModelProperty(notes = "Client info", required = true, position = 1)
    private Client client;

    @NotNull
    @PositiveOrZero
    @ApiModelProperty(notes = "Initial balance", example = "78.0", required = true, position = 1)
    private Double balance;
}
